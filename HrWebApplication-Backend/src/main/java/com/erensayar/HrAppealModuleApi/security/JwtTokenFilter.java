package com.erensayar.HrWebApplicationApi.security;

import com.erensayar.HrWebApplicationApi.error.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.contains("Bearer")) {
            token = authHeader.substring(7);
            try {
                username = jwtTokenManager.getUsernameToken(token);
            } catch (Exception e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (username != null && token != null && authentication == null) {
            if (jwtTokenManager.tokenValidate(token)) {
                UsernamePasswordAuthenticationToken upassToken =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(upassToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
