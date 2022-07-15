package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

public class CreatingFlightAddressDtoToFlightAddressConverter
        implements Converter<CreateFlightAddressRequestDto, FlightAddress> {
    @Override
    public FlightAddress convert(CreateFlightAddressRequestDto source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportName(source.getAirportName());
        flightAddress.setAirportAddress(source.getAirportAddress());
        return flightAddress;
    }
}
