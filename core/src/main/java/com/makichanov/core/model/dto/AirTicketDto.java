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
    private Long id;

    @Schema(description = "Plane number")
    private String planeNumber;

    @Schema(description = "Ticket price in dollars")
    private Double price;

    @Schema(description = "Ticket place in plane")
    private String place;

    @Schema(description = "Flight time in seconds")
    private Long flightTimeSeconds;

    @Schema(description = "Flight departure time")
    private Timestamp departureTime;

    @Schema(description = "Flight arrival time")
    private Timestamp arrivalTime;

    @Schema(description = "Departure address")
    private FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    private FlightAddressDto addressTo;

}
