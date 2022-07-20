package com.makichanov.core.converter;

import com.makichanov.core.entity.User;
import com.makichanov.core.model.request.UpdateUserRequestDto;
import org.springframework.core.convert.converter.Converter;

public class UpdateUserRequestDtoToUserConverter implements Converter<UpdateUserRequestDto, User> {
    @Override
    public User convert(UpdateUserRequestDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        return user;
    }
}
