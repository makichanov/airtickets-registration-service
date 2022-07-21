package com.makichanov.core.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Flight details response data")
public class FlightDetailsDto {

    @Schema(description = "Flight id")
    private Long id;

    @Schema(description = "Flight departure time")
    private LocalDateTime departureTime;

    @Schema(description = "Flight arrival time")
    private LocalDateTime arrivalTime;

    @Schema(description = "Flight base price")
    private Double basePrice;

    @Schema(description = "Flight max places")
    private Integer maxPlaces;

    @Schema(description = "Sold places number in flight")
    private Integer placesSold;

    @Schema(description = "Departure flight address")
    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightFrom;

    @Schema(description = "Arrival flight address")
    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightTo;

}
