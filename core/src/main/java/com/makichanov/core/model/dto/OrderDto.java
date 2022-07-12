package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@Schema(description = "Order dto")
public class OrderDto {

    @Schema(description = "Order id")
    Long id;

    @Schema(description = "Order total price in dollars")
    Double totalPrice;

    @Schema(description = "Order create date")
    Timestamp createDate;

    @Schema(description = "User that posted an order")
    Long userId;

    @Schema(description = "List of tickets in order")
    List<AirTicketDto> airTickets;

}
