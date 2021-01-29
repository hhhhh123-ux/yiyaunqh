package com.yiyuan.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yiyuan.demo.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gaozhiqiang
 */
@Slf4j
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("text/json;charset=utf-8");
        AjaxResult result = AjaxResult.failed("会话失效！请重新登录");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
