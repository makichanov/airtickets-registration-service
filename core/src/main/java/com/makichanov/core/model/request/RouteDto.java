package com.makichanov.core.model.request;

import com.makichanov.core.model.response.FlightAddressDto;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class RouteDto {

    @Positive(message = "Id value is always positive")
    private Long flightFromId;

    @Positive(message = "Id value is always positive")
    private Long flightToId;

    @Positive(message = "Tickets count should be positive")
    private Long ticketsCount;

}
