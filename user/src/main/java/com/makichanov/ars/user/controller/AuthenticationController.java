package com.makichanov.ars.user.controller;

import com.makichanov.ars.user.entity.User;
import com.makichanov.ars.user.mapper.UserMapper;
import com.makichanov.ars.user.model.AuthenticateRequest;
import com.makichanov.ars.user.model.UserDto;
import com.makichanov.ars.user.service.AuthenticationService;
import com.makichanov.ars.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "Create account, authenticate users")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Authentication", description = """
            Authenticate user by his credentials, returns user's JWT if successful
            """)
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticateRequest authenticatingDto) {
        String token = authenticationService.authenticate(authenticatingDto.getUsername(), authenticatingDto.getPassword());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    @Operation(summary = "Create account", description = """
            Create account and return created account data
            """)
    public ResponseEntity<UserDto> signup(@RequestBody @Valid AuthenticateRequest authenticateRequest) {
        User user = userMapper.toUser(authenticateRequest);
        User created = userService.create(user);

        return new ResponseEntity<>(userMapper.toDto(created), HttpStatus.CREATED);
    }
}
