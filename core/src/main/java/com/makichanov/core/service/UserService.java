package com.makichanov.core.service;

import com.makichanov.core.model.request.AuthenticateRequestDto;
import com.makichanov.core.entity.User;

import java.util.List;

public interface UserService {
    User find(Long id);

    List<User> findAll();

    User create(AuthenticateRequestDto authenticateRequestDto);

    User delete(Long deleteId);
}
