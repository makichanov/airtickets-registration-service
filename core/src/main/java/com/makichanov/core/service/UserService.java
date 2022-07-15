package com.makichanov.core.service;

import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.entity.User;

import java.util.List;

public interface UserService {
    User find(Long id);

    List<User> findAll();

    User create(AuthenticatingDto authenticatingDto);

    User delete(Long deleteId);
}
