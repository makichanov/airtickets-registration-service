package com.makichanov.core.model.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {

    private List<RouteDto> routes;

}
