package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Schema(description = "Dto for creating airticket entity")
public class CreatingAirTicketDto {

    @Schema(description = "Flight route name")
    String routeName;

    @Schema(description = "Ticket price in dollars")
    Double price;

    @Schema(description = "Total tickets on sale")
    Integer quantity;

    @Schema(description = "Flight departure time")
    Timestamp departureTime;

    @Schema(description = "Flight arrival time")
    Timestamp arrivalTime;

    @Schema(description = "Departure address")
    FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    FlightAddressDto addressTo;

}
