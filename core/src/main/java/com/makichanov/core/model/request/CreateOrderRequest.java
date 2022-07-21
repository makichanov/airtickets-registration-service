package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "Create order request data")
public class CreateOrderRequest {

    @Schema(description = "Set of unique routes which the user wants to order")
    private Set<Route> routes;

}
