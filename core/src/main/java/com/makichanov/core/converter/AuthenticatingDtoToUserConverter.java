package com.makichanov.core.converter;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//TODO: Настоятельно рекомендую использовать MapStruct
@RequiredArgsConstructor
public class AuthenticatingDtoToUserConverter implements Converter<AuthenticatingDto, User> {

    @Override
    public User convert(AuthenticatingDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        return user;
    }

}
