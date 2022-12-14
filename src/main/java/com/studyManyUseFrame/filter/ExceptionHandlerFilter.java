package com.studyManyUseFrame.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException re) {
            log.error("runtime exception handler filter");
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, re);
        }
    }

    public void setErrorResponse(HttpStatus status,HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        try {
            response.getWriter().println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
