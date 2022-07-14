package com.makichanov.core.converter;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

public class AirTicketToAirTicketDtoConverter implements Converter<AirTicket, AirTicketDto> {

    @Override
    public AirTicketDto convert(AirTicket source) {
        FlightAddressToFlightAddressDtoConverter flightAddressDtoConverter =
                new FlightAddressToFlightAddressDtoConverter();
        return AirTicketDto.builder()
                .id(source.getId())
                .planeNumber(source.getPlaneNumber())
                .price((double) source.getPrice() / 100)
                .place(source.getPlace())
                .build();
    }

}
