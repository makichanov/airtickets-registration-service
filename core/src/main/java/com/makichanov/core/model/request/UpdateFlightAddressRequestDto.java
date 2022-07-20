package com.makichanov.core.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateFlightAddressRequestDto {
    @NotBlank(message = "Airport name cannot be blank")
    private String airportAddress;

    @NotBlank(message = "Airport address cannot be blank")
    private String airportName;
}
