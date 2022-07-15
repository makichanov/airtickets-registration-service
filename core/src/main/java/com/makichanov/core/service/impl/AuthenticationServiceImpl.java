package com.makichanov.core.service.impl;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.service.AuthenticationService;
import com.makichanov.core.util.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(AuthenticatingDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return JwtTokenUtils.generateToken(userDetails.getUsername(), userDetails.getPassword());
    }
}
