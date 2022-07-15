package com.makichanov.core.controller;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.dto.UserDto;
import com.makichanov.core.model.entity.User;
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

    //TODO: Было бы хорошим тоном отличать dto, которые идут в запрос от dto, которые приходят в ответе. Рефактор на EntityNameRequest и EntityNameResponse
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticatingDto authenticatingDto) {
        String token = authenticationService.authenticate(authenticatingDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthenticatingDto authenticatingDto) {
        User user = userService.create(authenticatingDto);

        return new ResponseEntity<>(conversionService.convert(user, UserDto.class), HttpStatus.CREATED);
    }
}
