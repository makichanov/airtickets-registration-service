package com.makichanov.core.converter;

import com.makichanov.core.model.response.FlightAddressDto;
import com.makichanov.core.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

public class FlightAddressDtoToFlightAddressConverter implements Converter<FlightAddressDto, FlightAddress> {

    @Override
    public FlightAddress convert(FlightAddressDto source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportName(source.getAirportName());
        flightAddress.setAirportAddress(source.getAirportAddress());
        return flightAddress;
    }

}
