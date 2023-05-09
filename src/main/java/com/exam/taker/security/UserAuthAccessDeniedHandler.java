package com.exam.taker.security;


import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.RequestCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResponseInfo<String> responseInfo=new ResponseInfo();
        responseInfo.setCode(RequestCodeEnum.CODE_FORBIDDEN.getCode());
        responseInfo.setMessage(RequestCodeEnum.CODE_FORBIDDEN.getMessage());
        ResultUtil.responseJson(response,responseInfo);
    }
}
