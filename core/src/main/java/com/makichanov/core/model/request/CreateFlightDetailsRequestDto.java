package com.makichanov.core.model.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CreateFlightDetailsRequestDto {

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Double basePrice;

    private Long flightFromId;

    private Long flightToId;

    private Integer maxPlaces;

}
