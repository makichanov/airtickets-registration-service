package com.makichanov.core.converter;

import com.makichanov.core.model.request.CreateAirTicketRequest;
import com.makichanov.core.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

public class CreateAirTicketDtoToAirTicketConverter implements Converter<CreateAirTicketRequest, AirTicket> {

    @Override
    public AirTicket convert(CreateAirTicketRequest source) {
        AirTicket airTicket = new AirTicket();
        airTicket.setPrice((long) (source.getPrice() * 100));
        airTicket.setPlace(source.getPlace());
        return airTicket;
    }

}
