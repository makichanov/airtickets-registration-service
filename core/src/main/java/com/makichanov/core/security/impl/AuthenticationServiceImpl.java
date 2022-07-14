package com.makichanov.core.security.impl;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.security.AuthenticationService;
import com.makichanov.core.security.TokenService;
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
    private final TokenService tokenService;

    //TODO: Выкинуть DTO из сервисов, работа с DTO -- задача уровня представления
    @Override
    public String authenticate(AuthenticatingDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        //TODO: логические блоки разделяем пустыми строками
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //TODO: Пустая строка
        return tokenService.generateToken(userDetails.getUsername(), userDetails.getPassword());
    }

}
