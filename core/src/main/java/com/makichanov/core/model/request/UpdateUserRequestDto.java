package com.makichanov.core.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequestDto {
    @NotBlank(message = "Username should not be blank")
    @Size(min = 5, max = 50, message = "Username is supposed to be 5 - 50 symbols")
    String username;

    @NotBlank(message = "Password should not be blank")
    @Min(value = 12, message = "Password length less than 12 symbols is too weak")
    String password;
}
