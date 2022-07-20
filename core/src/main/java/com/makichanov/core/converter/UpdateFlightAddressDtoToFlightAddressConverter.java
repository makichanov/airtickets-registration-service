package com.makichanov.core.converter;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.model.request.UpdateFlightAddressRequestDto;
import org.springframework.core.convert.converter.Converter;

public class UpdateFlightAddressDtoToFlightAddressConverter
        implements Converter<UpdateFlightAddressRequestDto, FlightAddress> {
    @Override
    public FlightAddress convert(UpdateFlightAddressRequestDto source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportAddress(source.getAirportAddress());
        flightAddress.setAirportName(source.getAirportName());
        return flightAddress;
    }
}
