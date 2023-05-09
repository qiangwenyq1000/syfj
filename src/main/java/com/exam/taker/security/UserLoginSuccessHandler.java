package com.exam.taker.security;


import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.RequestCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = jwtUtil.generateToken(selfUserEntity);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        ResponseInfo<String> responseInfo=new ResponseInfo();
        responseInfo.setData(token);
        responseInfo.setCode(RequestCodeEnum.CODE_OK.getCode());
        responseInfo.setMessage("登录成功");
        ResultUtil.responseJson(response,responseInfo);
    }
}
