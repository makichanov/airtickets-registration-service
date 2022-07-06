package com.makichanov.core.service;

import com.makichanov.core.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto find(Long id);

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    UserDto delete(Long deleteId);

}
