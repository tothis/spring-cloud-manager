package com.example.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义过滤器
 * <p>
 * OncePerRequestFilter确保一次请求只过滤一次 不会重复执行
 *
 * @author 李磊
 * @since 1.0
 */
@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain) throws IOException, ServletException {
        String token = request.getParameter("token");
        log.info("token -> [{}]", token);
        chain.doFilter(request, response);
    }
}