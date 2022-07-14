package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

//TODO: @Data - избыточно, разбить на более мелкие аннотации
//TODO: Спецификаторы доступа для полей где?
@Data
@Builder
@Schema(description = "Airticket dto")
public class AirTicketDto {

    @Schema(description = "Ticket id")
    Long id;

    @Schema(description = "Flight route name")
    String routeName;

    @Schema(description = "Ticket price in dollars")
    Double price;

    @Schema(description = "Remaining tickets")
    Integer quantity;

    @Schema(description = "Flight time in seconds")
    Long flightTimeSeconds;

    @Schema(description = "Flight departure time")
    Timestamp departureTime;

    @Schema(description = "Flight arrival time")
    Timestamp arrivalTime;

    @Schema(description = "Departure address")
    FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    FlightAddressDto addressTo;

}
