//TODO: Разбиение по пакетам
// сервисы к сервисам
package com.makichanov.core.security;

public interface TokenService {

    String generateToken(String username, String password);

    boolean validateToken(String token);

    String getLoginFromToken(String token);
    // TODO: 7/14/22 Убирай пустый строки внизу

}
