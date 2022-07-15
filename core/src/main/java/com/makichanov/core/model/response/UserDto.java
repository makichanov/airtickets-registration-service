package com.makichanov.core.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@Schema(description = "User dto")
public class UserDto {

    @Schema(description = "User id")
    private Long id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "User password")
    private String password;

    @Schema(description = "User balance in dollars")
    private Double balance;

}
