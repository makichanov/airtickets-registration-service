package com.makichanov.core.converter;

import com.makichanov.core.model.response.UserDto;
import com.makichanov.core.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getPassword())
                .balance((double) source.getBalance() / 100)
                .build();
    }
}
