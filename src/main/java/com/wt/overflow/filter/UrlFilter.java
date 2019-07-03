package com.wt.overflow.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: liuxingyu
 * DATE: 2017-07-03.11:25
 * description:
 * version:
 */
@WebFilter(filterName = "UrlFilter",urlPatterns = "/*")
public class UrlFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length > 0){
//            for(Cookie cookie : cookies) {
//                if(cookie != null)
//                cookie.setPath("/");
//            }
//
//        }
        String[] headers = {"*"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(headers));
        String originHeader=request.getHeader("Origin");
        Collection<String> headerNames = response.getHeaderNames();
        //if (allowedOrigins.contains(originHeader)) {
        if (true) {
            response.setHeader("Access-Control-Allow-Origin", originHeader); //解决跨域访问报错http://192.168.1.14:8081
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            StringBuffer requestURL = request.getRequestURL();
        }

        chain.doFilter(req, resp);

    }

    public void destroy() {

    }
}
