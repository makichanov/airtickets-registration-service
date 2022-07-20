package com.makichanov.core.model.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AuthenticateRequestDto {

    @NotBlank(message = "Username should not be blank")
    @Size(min = 5, max = 50, message = "Username is supposed to be 5 - 50 symbols")
    private String username;

    @NotBlank(message = "Password should not be blank")
    @Min(value = 12, message = "Password length less than 12 symbols is too weak")
    private String password;

}
