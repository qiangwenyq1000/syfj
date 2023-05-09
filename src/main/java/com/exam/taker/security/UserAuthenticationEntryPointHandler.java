package com.exam.taker.security;


import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.RequestCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        ResponseInfo<String> responseInfo=new ResponseInfo();
        responseInfo.setCode(RequestCodeEnum.CODE_UNAUTHORIZED.getCode());
        responseInfo.setMessage(RequestCodeEnum.CODE_UNAUTHORIZED.getMessage());
        ResultUtil.responseJson(response,responseInfo);
    }
}
