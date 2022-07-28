package com.makichanov.core.controller;

import com.makichanov.core.entity.User;
import com.makichanov.core.model.request.AuthenticateRequest;
import com.makichanov.core.model.response.UserDto;
import com.makichanov.core.service.AuthenticationService;
import com.makichanov.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "Create account, authenticate users")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ConversionService conversionService;

    @PostMapping("/login")
    @Operation(summary = "Authentication", description = """
            Authenticate user by his credentials, returns user's JWT if successful
            """)
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticateRequest authenticatingDto) {
        String token = authenticationService.authenticate(authenticatingDto.getUsername(), authenticatingDto.getPassword());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @Operation(summary = "Create account", description = """
            Create account and return created account data
            """)
    public ResponseEntity<UserDto> signup(@RequestBody @Valid AuthenticateRequest authenticateRequest) {
        User user = conversionService.convert(authenticateRequest, User.class);
        User created = userService.create(user);

        return new ResponseEntity<>(conversionService.convert(created, UserDto.class), HttpStatus.CREATED);
    }
}
