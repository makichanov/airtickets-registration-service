package com.makichanov.core.model.request;

import com.makichanov.core.model.response.FlightAddressDto;
import lombok.Data;

@Data
public class RouteDto {

    private Long flightFromId;

    private Long flightToId;

}
