package com.makichanov.core.converter;

import com.makichanov.core.model.response.FlightAddressDto;
import com.makichanov.core.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

public class FlightAddressToFlightAddressDtoConverter implements Converter<FlightAddress, FlightAddressDto> {

    @Override
    public FlightAddressDto convert(FlightAddress source) {
        return FlightAddressDto.builder()
                .id(source.getId())
                .airportAddress(source.getAirportAddress())
                .airportName(source.getAirportName())
                .build();
    }

}
