package com.makichanov.core.converter;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//TODO: Настоятельно рекомендую использовать MapStruct
@Component
@RequiredArgsConstructor
public class AuthenticatingDtoToUserConverter implements Converter<AuthenticatingDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(AuthenticatingDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        //TODO: что делает бизнес-логика в конвертере? encode пароля вынести на уровень сервиса
        user.setPassword(passwordEncoder.encode(source.getPassword()));
        return user;
    }

}
