package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CreatingAirTicketDto {

    String routeName;

    Double price;

    Integer quantity;

    Timestamp departureTime;

    Timestamp arrivalTime;

    FlightAddressDto addressFrom;

    FlightAddressDto addressTo;

}
