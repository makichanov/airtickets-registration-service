package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AirTicketDto {

    Long id;

    String routeName;

    Double price;

    Integer quantity;

    Long flightTimeSeconds;

    Timestamp departureTime;

    Timestamp arrivalTime;

    FlightAddressDto addressFrom;

    FlightAddressDto addressTo;

}
