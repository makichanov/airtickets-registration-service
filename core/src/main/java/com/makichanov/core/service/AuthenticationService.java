package com.makichanov.core.service;

import com.makichanov.core.model.dto.AuthenticatingDto;

public interface AuthenticationService {
    String authenticate(AuthenticatingDto dto);
}
