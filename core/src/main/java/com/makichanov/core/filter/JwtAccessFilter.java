package com.makichanov.core.filter;

import com.makichanov.core.util.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessFilter extends GenericFilter {
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = JwtTokenUtils.extractJwtFromHeader(((HttpServletRequest)request).getHeader("Authorization"));

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
