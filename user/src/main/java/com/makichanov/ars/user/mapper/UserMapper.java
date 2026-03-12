package com.makichanov.ars.user.mapper;

import com.makichanov.ars.user.entity.User;
import com.makichanov.ars.user.model.AuthenticateRequest;
import com.makichanov.ars.user.model.UpdateUserRequest;
import com.makichanov.ars.user.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UpdateUserRequest updateUserRequest);

    User toUser(AuthenticateRequest authenticateRequest);

    List<UserDto> toUserDtoList(List<User> users);
}
