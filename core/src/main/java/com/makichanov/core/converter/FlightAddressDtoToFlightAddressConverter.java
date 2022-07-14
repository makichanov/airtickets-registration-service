package com.makichanov.core.converter;

import com.makichanov.core.model.dto.FlightAddressDto;
import com.makichanov.core.model.entity.FlightAddress;
import org.springframework.core.convert.converter.Converter;

//TODO: Смотри TODO в других конвертерах
public class FlightAddressDtoToFlightAddressConverter implements Converter<FlightAddressDto, FlightAddress> {

    @Override
    public FlightAddress convert(FlightAddressDto source) {
        FlightAddress flightAddress = new FlightAddress();
        flightAddress.setAirportName(source.getAirportName());
        flightAddress.setAirportAddress(source.getAirportAddress());
        return flightAddress;
    }

}
