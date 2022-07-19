package com.makichanov.core.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class FlightDetailsDto {

    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Double basePrice;

    private Integer maxPlaces;

    private Integer placesSold;

    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightFrom;

    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightTo;

}
