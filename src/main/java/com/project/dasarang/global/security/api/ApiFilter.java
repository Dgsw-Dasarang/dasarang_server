package com.project.dasarang.global.security.api;

import com.project.dasarang.global.security.jwt.exception.ApiKeyNotfoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ApiFilter extends OncePerRequestFilter {

    private final ApiProvider apiProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = request.getHeader("key");
        String path = request.getServletPath();

        log.info("api path : " + path);
        if(path.contains(".html")) {
            filterChain.doFilter(request, response);
            return;
        } else if(path.contains(".ico")) {
            filterChain.doFilter(request, response);
            return;
        } else if(path.contains("api")) {
            filterChain.doFilter(request, response);
            return;
        }else {
            if(key != null)
                apiProvider.checkKey(key);
            else
                throw ApiKeyNotfoundException.EXCEPTION;
        }

        filterChain.doFilter(request, response);
    }
}
