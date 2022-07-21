package com.makichanov.core.converter;

import com.makichanov.core.entity.User;
import com.makichanov.core.model.request.UpdateUserRequest;
import org.springframework.core.convert.converter.Converter;

public class UpdateUserRequestDtoToUserConverter implements Converter<UpdateUserRequest, User> {
    @Override
    public User convert(UpdateUserRequest source) {
        User user = new User();
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        return user;
    }
}
