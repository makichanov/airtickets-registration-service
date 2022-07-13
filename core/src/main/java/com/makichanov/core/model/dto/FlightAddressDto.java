package com.makichanov.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Dto to describe departure/destination flight addresses")
public class FlightAddressDto {

    @Schema(description = "Address id")
    private Long id;

    @Schema(description = "Airport name")
    private String airportName;

    @Schema(description = "Airport local address")
    private String airportAddress;

}
