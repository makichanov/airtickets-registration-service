package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

//TODO: Cмотри AirTicketDto
@Data
@Builder
@Schema(description = "Dto for creating airticket entity")
public class CreatingAirTicketDto {

    @Schema(description = "Plane number")
    private String planeNumber;

    @Schema(description = "Ticket price in dollars")
    private Double price;

    @Schema(description = "Ticket place in plane")
    private String place;

    @Schema(description = "Flight departure time")
    private Timestamp departureTime;

    @Schema(description = "Flight arrival time")
    private Timestamp arrivalTime;

    @Schema(description = "Departure address")
    private FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    private FlightAddressDto addressTo;

}
