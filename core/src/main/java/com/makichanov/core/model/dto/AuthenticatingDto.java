package com.makichanov.core.model.dto;

import lombok.Data;

//TODO: @Data - избыточно, разбить на более мелкие аннотации
//TODO: Спецификаторы доступа для полей где?
@Data
public class AuthenticatingDto {

    String username;

    String password;

}
