package com.makichanov.core.model.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CreateOrderRequestDto {

    private Set<RouteDto> routes;

}
