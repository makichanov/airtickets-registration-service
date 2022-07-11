package com.makichanov.core.converter;

import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.model.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

import java.util.concurrent.TimeUnit;

public class CreatingAirTicketToAirTicketConverter implements Converter<CreatingAirTicketDto, AirTicket> {

    @Override
    public AirTicket convert(CreatingAirTicketDto source) {
        FlightAddressDtoToFlightAddressConverter converter = new FlightAddressDtoToFlightAddressConverter();
        long arrivalSeconds = TimeUnit.MILLISECONDS.toSeconds(source.getArrivalTime().getTime());
        long departureSeconds = TimeUnit.MILLISECONDS.toSeconds(source.getDepartureTime().getTime());
        Long flightTimeSeconds = arrivalSeconds - departureSeconds;
        AirTicket airTicket = new AirTicket();
        airTicket.setRouteName(source.getRouteName());
        airTicket.setPrice((long) (source.getPrice() * 100));
        airTicket.setQuantity(source.getQuantity());
        airTicket.setDepartureTime(source.getDepartureTime());
        airTicket.setArrivalTime(source.getArrivalTime());
        airTicket.setAddressFrom(converter.convert(source.getAddressFrom()));
        airTicket.setAddressTo(converter.convert(source.getAddressTo()));
        airTicket.setFlightTimeSeconds(flightTimeSeconds);
        return airTicket;
    }

}
