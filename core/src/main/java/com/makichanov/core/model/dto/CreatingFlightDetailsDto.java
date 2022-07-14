package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CreatingFlightDetailsDto {

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private Double basePrice;

    private Long flightFromId;

    private Long flightToId;

}
