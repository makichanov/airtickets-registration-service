package com.makichanov.core.model.request;

import lombok.Data;

@Data
public class AuthenticateRequestDto {

    private String username;

    private String password;

}
