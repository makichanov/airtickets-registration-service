package com.makichanov.core.model.request;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "Username should not be blank")
    @Size(min = 5, max = 50, message = "Username is supposed to be 5 - 50 symbols")
    String username;

    @NotBlank(message = "Password should not be blank")
    @Min(value = 12, message = "Password length less than 12 symbols is too weak")
    String password;
}
