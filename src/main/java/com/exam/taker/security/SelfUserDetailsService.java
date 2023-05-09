package com.exam.taker.security;


import com.exam.taker.domain.User;
import com.exam.taker.service.SysLogService;
import com.exam.taker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {

/*        EntityWrapper<Users> wrapper = new EntityWrapper<>();*/


        //邮箱正则表达式
       // String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        //是否为邮箱
     /*   if (username.matches(expr)) {
            wrapper.eq("user_email", username);
        } else {
            wrapper.eq("user_login", username);
        }*/

        // 查询用户信息
        User user = userService.getUserByHone(username);
        if (user != null) {
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity(user);
            return selfUserEntity;
        }
        return null;
    }
}
