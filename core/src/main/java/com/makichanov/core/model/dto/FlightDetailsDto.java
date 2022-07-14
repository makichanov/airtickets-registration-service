package com.makichanov.core.model.dto;

import com.makichanov.core.model.entity.AirTicket;
import com.makichanov.core.model.entity.FlightAddress;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@Builder
public class FlightDetailsDto {

    private Long id;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private Double basePrice;

    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightFrom;

    @EqualsAndHashCode.Exclude
    private FlightAddressDto flightTo;

}
