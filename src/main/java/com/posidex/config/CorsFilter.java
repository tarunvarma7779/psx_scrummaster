//package com.posidex.config;
//
//import java.io.IOException;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class CorsFilter implements jakarta.servlet.Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Max-Age", "3600");
//
//        chain.doFilter(request, response);
//    }
//
//    // Other methods (init, destroy) can be left empty
//}
//
