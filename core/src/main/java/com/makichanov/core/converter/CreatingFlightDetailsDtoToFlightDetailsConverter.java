package com.makichanov.core.converter;

import com.makichanov.core.model.dto.CreatingFlightDetailsDto;
import com.makichanov.core.model.entity.FlightDetails;
import org.springframework.core.convert.converter.Converter;

public class CreatingFlightDetailsDtoToFlightDetailsConverter
        implements Converter<CreatingFlightDetailsDto, FlightDetails> {

    @Override
    public FlightDetails convert(CreatingFlightDetailsDto source) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setDepartureTime(source.getDepartureTime());
        flightDetails.setArrivalTime(source.getArrivalTime());
        flightDetails.setBasePrice((long) (source.getBasePrice() * 100));
        return flightDetails;
    }

}
