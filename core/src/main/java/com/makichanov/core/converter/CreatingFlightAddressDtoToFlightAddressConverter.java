package com.makichanov.core.converter;

import com.makichanov.core.model.dto.CreatingFlightAddressDto;
import com.makichanov.core.model.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

public class CreatingFlightAddressDtoToFlightAddressConverter
        implements Converter<CreatingFlightAddressDto, FlightAddress> {
    @Override
    public FlightAddress convert(CreatingFlightAddressDto source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportName(source.getAirportName());
        flightAddress.setAirportAddress(source.getAirportAddress());
        return flightAddress;
    }
}
