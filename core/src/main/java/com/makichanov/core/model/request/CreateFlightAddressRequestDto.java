package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateFlightAddressRequestDto {

    @Schema(description = "Airport name")
    @NotBlank(message = "Airport name cannot be blank")
    private String airportName;

    @Schema(description = "Airport local address")
    @NotBlank(message = "Airport address cannot be blank")
    private String airportAddress;

}
