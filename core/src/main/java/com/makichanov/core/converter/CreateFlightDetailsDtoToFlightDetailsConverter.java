package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateFlightDetailsRequest;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.core.convert.converter.Converter;

public class CreateFlightDetailsDtoToFlightDetailsConverter
        implements Converter<CreateFlightDetailsRequest, FlightDetails> {

    @Override
    public FlightDetails convert(CreateFlightDetailsRequest source) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setDepartureTime(source.getDepartureTime());
        flightDetails.setArrivalTime(source.getArrivalTime());
        flightDetails.setBasePrice((long) (source.getBasePrice() * 100));
        flightDetails.setPlaces(source.getPlaces());
        return flightDetails;
    }

}
