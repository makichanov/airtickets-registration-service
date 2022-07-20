package com.makichanov.core.model.request;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    String username;

    String password;
}
