package com.makichanov.core.service;

import com.makichanov.core.model.request.AuthenticatingDto;

public interface AuthenticationService {
    String authenticate(AuthenticatingDto dto);
}
