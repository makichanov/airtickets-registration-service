package com.makichanov.core.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

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

    @Schema(description = "Flight details")
    private FlightDetailsDto flightDetails;

}
