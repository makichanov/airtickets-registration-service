package com.makichanov.ars.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

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

}
