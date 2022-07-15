package com.makichanov.core.model.response;

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
    private Long id;

    @Schema(description = "Order total price in dollars")
    private Double totalPrice;

    @Schema(description = "Order create date")
    private Timestamp createDate;

    @Schema(description = "User that posted an order")
    private Long userId;

    @Schema(description = "List of tickets in order")
    private List<AirTicketDto> airTickets;

}
