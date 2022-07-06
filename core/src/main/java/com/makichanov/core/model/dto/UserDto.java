package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    Long id;

    String username;

    String password;

    Double balance;

    List<OrderDto> orders;

}
