package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Update flight address request data")
public class UpdateFlightAddressRequest {

    @NotBlank(message = "Airport name cannot be blank")
    @Schema(description = "New airport address")
    private String airportAddress;

    @NotBlank(message = "Airport address cannot be blank")
    @Schema(description = "New airport name")
    private String airportName;
}
