package com.makichanov.core.converter;

import com.makichanov.core.model.dto.FlightAddressDto;
import com.makichanov.core.model.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

//TODO: Смотри TODO в других конвертерах
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
