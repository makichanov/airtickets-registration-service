package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.core.convert.converter.Converter;

public class CreatingFlightDetailsDtoToFlightDetailsConverter
        implements Converter<CreateFlightDetailsRequestDto, FlightDetails> {

    @Override
    public FlightDetails convert(CreateFlightDetailsRequestDto source) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setDepartureTime(source.getDepartureTime());
        flightDetails.setArrivalTime(source.getArrivalTime());
        flightDetails.setBasePrice((long) (source.getBasePrice() * 100));
        return flightDetails;
    }

}
