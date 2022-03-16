package com.example.fcmpractice.global.error.exception;

import com.example.fcmpractice.global.error.FCMPracticeException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        } catch (FCMPracticeException e) {
            String errorResponse = ErrorResponse.builder()
                    .status(e.getErrorCode().getStatus())
                    .code(e.getErrorCode().getCode())
                    .message(e.getErrorCode().getMessage())
                    .build().toString();

            response.setStatus(e.getErrorCode().getStatus());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(errorResponse);
        }
    }
}
