package com.exam.taker.security;


import com.exam.taker.domain.User;
import com.exam.taker.service.SysLogService;
import com.exam.taker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    @Autowired
//    private UserLogService logService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        User userInfo = userService.getUserByHone(userName);


        if (userInfo==null) {
            sysLogService.saveManage("login","0",userName,"用户名不存在");
          //  logService.log("user","login","登录","登录失败：用户不存在","","",0,userName);
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(!redisTemplate.hasKey(userName)){
            sysLogService.saveManage("login","0",userName,"验证码错误");
            throw new BadCredentialsException("验证码错误");
        }
        String passCode=redisTemplate.opsForValue().get(userName).toString();
        if(!password.equals(passCode)){
            sysLogService.saveManage("login","0",userName,"验证码错误");
            throw new BadCredentialsException("验证码错误");
        }

        redisTemplate.delete(userName);//验证码使用后删除
        SelfUserEntity selfUserEntity=new SelfUserEntity(userInfo);

        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
//        if (!new BCryptPasswordEncoder().matches(password, new BCryptPasswordEncoder().encode(selfUserEntity.getPassword()))) {
//           // logService.log("user","login","登录","登录失败：密码错误","","",selfUserEntity.getUserId(),selfUserEntity.getUsername());
//            throw new BadCredentialsException("密码不正确");
//        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
//        if (!selfUserEntity.isEnabled()) {
//          //  logService.log("user","login","登录","登录失败：用户被禁用","","",selfUserEntity.getUserId(),selfUserEntity.getUsername());
//            throw new LockedException("该用户已被禁用");
//        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();

       /* EntityWrapper<Usermeta> roleWrapper = new EntityWrapper<>();
        roleWrapper.eq("user_id",userInfo.getUserId());
        roleWrapper.eq("meta_key","wp_user_level");
        // 查询用户角色
        List<Usermeta> sysRoleEntityList = usermetaService.selectList(roleWrapper);
        for (Usermeta sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getMetaValue()));
        }
        userInfo.setAuthorities(authorities);*/
        // 进行登录
       //logService.log("user","login","登录","登录成功","","",selfUserEntity.getUserId(),selfUserEntity.getUsername());
        sysLogService.saveManage("login","1",userName,"登录成功");
        return new UsernamePasswordAuthenticationToken(selfUserEntity, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
