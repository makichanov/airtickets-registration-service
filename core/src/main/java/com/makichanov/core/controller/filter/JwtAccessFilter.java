package com.makichanov.core.controller.filter;

import com.makichanov.core.util.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = JwtTokenUtils.extractJwtFromHeader(request.getHeader("Authorization"));
        if (jwt != null && JwtTokenUtils.validateToken(jwt)) {
            String login = JwtTokenUtils.getLoginFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            if (userDetails != null) {
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
