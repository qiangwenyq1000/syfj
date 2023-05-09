package com.exam.taker.security;

import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.RequestCodeEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        ResponseInfo<String> responseInfo=new ResponseInfo();
        responseInfo.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
        if (exception instanceof UsernameNotFoundException){
            System.out.println("【登录失败】"+exception.getMessage());
            responseInfo.setMessage("用户名不存在");
            ResultUtil.responseJson(response,responseInfo);
        }
        if (exception instanceof LockedException){
            System.out.println("【登录失败】"+exception.getMessage());
            responseInfo.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
            responseInfo.setMessage("用户被冻结");
            ResultUtil.responseJson(response,responseInfo);
        }
        if (exception instanceof BadCredentialsException){
            System.out.println("【登录失败】"+exception.getMessage());
            responseInfo.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
            responseInfo.setMessage("密码错误");
           ResultUtil.responseJson(response,responseInfo);
        }
        ResultUtil.responseJson(response,responseInfo);
    }
}
