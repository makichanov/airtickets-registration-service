package com.makichanov.core.security;

import com.makichanov.core.model.dto.AuthenticatingDto;

public interface AuthenticationService {

    String authenticate(AuthenticatingDto dto);

}
