package com.makichanov.core.security;

import com.makichanov.core.model.dto.AuthenticatingDto;

//TODO: Рекомендую отказаться от интерфейсной модели сервисов
public interface AuthenticationService {

    String authenticate(AuthenticatingDto dto);

}
