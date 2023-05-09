package com.exam.taker.security;

import cn.hutool.extra.spring.SpringUtil;
import com.exam.taker.constant.AuthConstants;
import com.exam.taker.util.SpringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    private static RedisTemplate<String,Object> redisTemplate= SpringUtils.getBean("redisTemplate");

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.tokenHeader);
        if (tokenHeader==null || tokenHeader.equals("")){
            tokenHeader=request.getParameter(JWTConfig.tokenHeader);
        }

        if (null != tokenHeader && tokenHeader.startsWith(JWTConfig.tokenPrefix)) {
            try {
                BoundSetOperations boundSetOperations=redisTemplate.boundSetOps(AuthConstants.TOKEN_BLACK_MAP);
                Set blackToken=null;
                if(boundSetOperations !=null){
                    blackToken=redisTemplate.boundSetOps(AuthConstants.TOKEN_BLACK_MAP).members();
                }
                if(blackToken ==null || !blackToken.contains(tokenHeader)){
                    // 截取JWT前缀
                    String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
                    // 解析JWT
                    Claims claims = Jwts.parser()
                            .setSigningKey(JWTConfig.secret)
                            .parseClaimsJws(token)
                            .getBody();
                    // 获取用户名
                    String username = claims.getSubject();
                    String userId = claims.getId();


                    if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                        // 获取角色
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        SelfUserEntity selfUserEntity = new SelfUserEntity();
                        selfUserEntity.setUsername(claims.getSubject());
                        selfUserEntity.setUserId(claims.getId());
                        selfUserEntity.setAuthorities(authorities);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            } catch (ExpiredJwtException e) {
                System.out.println("Token过期");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}
