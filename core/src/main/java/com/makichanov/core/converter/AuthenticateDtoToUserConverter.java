package com.makichanov.core.converter;

import com.makichanov.core.model.request.AuthenticateRequest;
import com.makichanov.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class AuthenticateDtoToUserConverter implements Converter<AuthenticateRequest, User> {

    @Override
    public User convert(AuthenticateRequest source) {
        User user = new User();
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        return user;
    }

}
