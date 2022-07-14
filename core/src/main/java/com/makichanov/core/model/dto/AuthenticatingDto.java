package com.makichanov.core.model.dto;

import lombok.Data;

//TODO: @Data - избыточно, разбить на более мелкие аннотации
@Data
public class AuthenticatingDto {

    private String username;

    private String password;

}
