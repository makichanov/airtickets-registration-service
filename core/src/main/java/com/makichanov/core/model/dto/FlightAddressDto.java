package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
//TODO: Cмотри AirTicketDto
@Data
@Builder
@Schema(description = "Dto to describe departure/destination flight addresses")
public class FlightAddressDto {

    @Schema(description = "Address id")
    Long id;

    @Schema(description = "Airport name")
    String airportName;

    @Schema(description = "Airport local address")
    String airportAddress;

}
