package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Positive;

// TODO: 7/28/22 Зачем этот класс?
// Для удобного и логичного оформления заказа. Пользователь должен указать место отправки,
// назначения и количество билетов
@Data
@Schema(description = "Route order data")
public class Route {

    @Positive(message = "Id value is always positive")
    @Schema(description = "Flight departure address id")
    private Long flightFromId;

    @Positive(message = "Id value is always positive")
    @Schema(description = "Flight arrival address id")
    private Long flightToId;

    @Positive(message = "Tickets count should be positive")
    @Schema(description = "Amount of tickets user wants to order")
    private Long ticketsCount;

}
