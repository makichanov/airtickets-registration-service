package com.makichanov.core.model.request;

import lombok.Data;

@Data
public class UpdateFlightAddressRequestDto {
    private String airportAddress;

    private String airportName;
}
