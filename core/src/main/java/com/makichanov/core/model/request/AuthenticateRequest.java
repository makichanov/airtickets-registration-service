package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Authentication request data for creating user and authenticating in system")
public class AuthenticateRequest {

    @NotBlank(message = "Username should not be blank")
    @Size(min = 5, max = 50, message = "Username is supposed to be 5 - 50 symbols")
    @Schema(description = "User's username, should be in bounds 5 - 50 symbols")
    private String username;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "Password length less than 5 symbols is too weak")
    @Schema(description = "User's password, not less than 12 symbols")
    private String password;

}
