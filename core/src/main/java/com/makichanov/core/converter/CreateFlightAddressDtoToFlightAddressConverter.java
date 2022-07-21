package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateFlightAddressRequest;
import com.makichanov.core.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

public class CreateFlightAddressDtoToFlightAddressConverter
        implements Converter<CreateFlightAddressRequest, FlightAddress> {
    @Override
    public FlightAddress convert(CreateFlightAddressRequest source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportName(source.getAirportName());
        flightAddress.setAirportAddress(source.getAirportAddress());
        return flightAddress;
    }
}
