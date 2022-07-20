package com.makichanov.core.service;

import com.makichanov.core.model.request.AuthenticateRequestDto;
import com.makichanov.core.util.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public String authenticate(String username, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return JwtTokenUtils.generateToken(userDetails.getUsername(), userDetails.getPassword());
    }
}
