package com.exam.taker.security;


import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.RequestCodeEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        ResponseInfo<String> responseInfo=new ResponseInfo();
        responseInfo.setCode(RequestCodeEnum.CODE_OK.getCode());
        responseInfo.setMessage("退出成功");
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response,responseInfo);
    }
}
