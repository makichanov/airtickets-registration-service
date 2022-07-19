package com.makichanov.core.converter;

import com.makichanov.core.model.response.FlightDetailsDto;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.core.convert.converter.Converter;

public class FlightDetailsToFlightDetailsDtoConverter implements Converter<FlightDetails, FlightDetailsDto> {
    @Override
    public FlightDetailsDto convert(FlightDetails source) {
        FlightAddressToFlightAddressDtoConverter converter = new FlightAddressToFlightAddressDtoConverter();
        return FlightDetailsDto.builder()
                .id(source.getId())
                .departureTime(source.getDepartureTime())
                .arrivalTime(source.getArrivalTime())
                .basePrice((double) source.getBasePrice() / 100)
                .flightFrom(converter.convert(source.getFlightFrom()))
                .flightTo(converter.convert(source.getFlightTo()))
                .maxPlaces(source.getMaxPlaces())
                .placesSold(source.getPlacesSold())
                .build();
    }
}
