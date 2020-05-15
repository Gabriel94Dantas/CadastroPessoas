package com.prova.mirante.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prova.mirante.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

public class JWTFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getRequestURI().startsWith("/CadastroPessoas/api/login")
        	|| req.getRequestURI().endsWith("/CadastroPessoas/")
        	|| req.getRequestURI().endsWith("login.html")
        	|| req.getRequestURI().startsWith("/CadastroPessoas/vendor/")
        	|| req.getRequestURI().startsWith("/CadastroPessoas/js/")
        	|| req.getRequestURI().startsWith("/CadastroPessoas/css/")
        	|| req.getRequestURI().startsWith("/CadastroPessoas/fonts/")
        	|| req.getRequestURI().startsWith("/CadastroPessoas/images/")){
        	
            filterChain.doFilter(servletRequest, servletResponse);
            
            return;
        }

        String token = req.getHeader(JWTUtil.TOKEN_HEADER);

        if(token == null || token.trim().isEmpty()){
            res.setStatus(401);
            return;
        }

        try {
            Jws<Claims> parser = JWTUtil.decode(token);
            System.out.println("User request: "+ parser.getBody().getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureException e) {
            res.setStatus(401);
        }

    }

    @Override
    public void destroy() {}
}