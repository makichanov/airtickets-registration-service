package com.makichanov.core.model.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CreateFlightDetailsRequestDto {

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private Double basePrice;

    private Long flightFromId;

    private Long flightToId;

}
