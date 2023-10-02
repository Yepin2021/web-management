package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            //response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.getWriter().write(json);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
