package com.makichanov.core.converter;

import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.UpdateFlightDetailsRequestDto;
import org.springframework.core.convert.converter.Converter;

public class UpdateFlightDetailsDtoToFlightDetailsConverter
        implements Converter<UpdateFlightDetailsRequestDto, FlightDetails> {
    @Override
    public FlightDetails convert(UpdateFlightDetailsRequestDto source) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setDepartureTime(source.getDepartureTime());
        flightDetails.setArrivalTime(source.getArrivalTime());
        flightDetails.setBasePrice((long) (source.getBasePrice() * 100));
        flightDetails.setMaxPlaces(source.getMaxPlaces());
        flightDetails.setPlacesSold(source.getPlacesSold());
        return flightDetails;
    }
}
