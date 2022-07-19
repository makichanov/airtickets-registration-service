package com.makichanov.core.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateFlightDetailsRequestDto {
    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Double basePrice;

    private Integer maxPlaces;

    private Integer placesSold;

    private Long flightFromId;

    private Long flightToId;
}
