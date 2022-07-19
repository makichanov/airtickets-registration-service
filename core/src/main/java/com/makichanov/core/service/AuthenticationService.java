package com.makichanov.core.service;

import com.makichanov.core.model.request.AuthenticateRequestDto;

public interface AuthenticationService {
    String authenticate(AuthenticateRequestDto dto);
}
