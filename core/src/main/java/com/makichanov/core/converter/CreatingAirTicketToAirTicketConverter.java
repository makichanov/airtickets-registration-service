package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateAirTicketRequestDto;
import com.makichanov.core.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

import java.util.concurrent.TimeUnit;

public class CreatingAirTicketToAirTicketConverter implements Converter<CreateAirTicketRequestDto, AirTicket> {

    @Override
    public AirTicket convert(CreateAirTicketRequestDto source) {
        AirTicket airTicket = new AirTicket();
        airTicket.setPrice((long) (source.getPrice() * 100));
        airTicket.setPlace(source.getPlace());
        return airTicket;
    }

}
