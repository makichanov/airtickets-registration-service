package com.makichanov.core.converter;

import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.UpdateFlightDetailsRequest;
import org.springframework.core.convert.converter.Converter;

public class UpdateFlightDetailsDtoToFlightDetailsConverter
        implements Converter<UpdateFlightDetailsRequest, FlightDetails> {
    @Override
    public FlightDetails convert(UpdateFlightDetailsRequest source) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setDepartureTime(source.getDepartureTime());
        flightDetails.setArrivalTime(source.getArrivalTime());
        flightDetails.setBasePrice((long) (source.getBasePrice() * 100));
        flightDetails.setPlaces(source.getPlaces());
        return flightDetails;
    }
}
