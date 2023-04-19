package com.makichanov.core.converter;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.model.request.UpdateFlightAddressRequest;
import org.springframework.core.convert.converter.Converter;

public class UpdateFlightAddressDtoToFlightAddressConverter
        implements Converter<UpdateFlightAddressRequest, FlightAddress> {
    @Override
    public FlightAddress convert(UpdateFlightAddressRequest source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportAddress(source.getAirportAddress());
        flightAddress.setAirportName(source.getAirportName());
        return flightAddress;
    }
}
