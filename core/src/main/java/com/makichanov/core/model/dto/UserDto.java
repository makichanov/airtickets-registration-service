package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
//TODO: Cмотри AirTicketDto
@Data
@Builder
@Schema(description = "User dto")
public class UserDto {

    @Schema(description = "User id")
    Long id;

    @Schema(description = "Username")
    String username;

    @Schema(description = "User password")
    String password;

    @Schema(description = "User balance in dollars")
    Double balance;

    @Schema(description = "User orders")
    List<OrderDto> orders;

}
