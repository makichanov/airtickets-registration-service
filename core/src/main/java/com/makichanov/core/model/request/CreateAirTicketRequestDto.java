package com.makichanov.core.model.request;

import com.makichanov.core.model.response.FlightAddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Schema(description = "Dto for creating airticket entity")
public class CreateAirTicketRequestDto {

    @Schema(description = "Plane number")
    private String planeNumber;

    @Schema(description = "Ticket price in dollars")
    private Double price;

    @Schema(description = "Ticket place in plane")
    private Integer place;

    @Schema(description = "Flight departure time")
    private LocalDateTime departureTime;

    @Schema(description = "Flight arrival time")
    private LocalDateTime arrivalTime;

    @Schema(description = "Departure address")
    private FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    private FlightAddressDto addressTo;

}
