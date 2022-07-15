package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateFlightAddressRequestDto {

    @Schema(description = "Airport name")
    private String airportName;

    @Schema(description = "Airport local address")
    private String airportAddress;

}
