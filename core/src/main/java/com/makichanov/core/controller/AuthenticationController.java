package com.makichanov.core.controller;

import com.makichanov.core.model.request.AuthenticateRequestDto;
import com.makichanov.core.model.response.UserDto;
import com.makichanov.core.entity.User;
import com.makichanov.core.service.AuthenticationService;
import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ConversionService conversionService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticateRequestDto authenticatingDto) {
        String token = authenticationService.authenticate(authenticatingDto.getUsername(), authenticatingDto.getPassword());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthenticateRequestDto authenticateRequestDto) {
        User user = conversionService.convert(authenticateRequestDto, User.class);
        User created = userService.create(user);

        return new ResponseEntity<>(conversionService.convert(created, UserDto.class), HttpStatus.CREATED);
    }
}
