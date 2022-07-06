package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FlightAddressDto {

    Long id;

    String airportName;

    String airportAddress;

}
