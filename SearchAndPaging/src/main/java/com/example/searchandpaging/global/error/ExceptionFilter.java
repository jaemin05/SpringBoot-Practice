package com.example.searchandpaging.global.error;

import com.example.searchandpaging.global.error.exception.CustomException;
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

        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            String errorResponse = String.valueOf(
                    ErrorResponse.builder()
                            .status(e.getErrorCode().getStatus())
                            .code(e.getErrorCode().getCode())
                            .message(e.getErrorCode().getMessage())
                            .build());

            response.setStatus(e.getErrorCode().getStatus());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(errorResponse);
        }
    }
}
