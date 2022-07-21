package com.makichanov.core.converter;

import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.model.request.UpdateAirTicketRequest;
import org.springframework.core.convert.converter.Converter;

public class UpdateAirTicketRequestDtoToAirTicketConverter implements Converter<UpdateAirTicketRequest, AirTicket> {
    @Override
    public AirTicket convert(UpdateAirTicketRequest source) {
        AirTicket airTicket = new AirTicket();
        airTicket.setPlace(source.getPlace());
        return airTicket;
    }
}
