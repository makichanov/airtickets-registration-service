package com.makichanov.core.converter;

import com.makichanov.core.model.request.AuthenticatingDto;
import com.makichanov.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class AuthenticatingDtoToUserConverter implements Converter<AuthenticatingDto, User> {

    @Override
    public User convert(AuthenticatingDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        return user;
    }

}
