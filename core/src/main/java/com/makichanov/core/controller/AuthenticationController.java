package com.makichanov.core.controller;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.dto.UserDto;
import com.makichanov.core.security.AuthenticationService;
import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthenticatingDto authenticatingDto) {
        return authenticationService.authenticate(authenticatingDto);
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody AuthenticatingDto authenticatingDto) {
        return userService.create(authenticatingDto);
    }

}
