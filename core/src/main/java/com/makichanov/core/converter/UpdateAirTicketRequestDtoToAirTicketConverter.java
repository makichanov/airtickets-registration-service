package com.makichanov.core.converter;

import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.model.request.UpdateAirTicketRequestDto;
import org.springframework.core.convert.converter.Converter;

public class UpdateAirTicketRequestDtoToAirTicketConverter implements Converter<UpdateAirTicketRequestDto, AirTicket> {
    @Override
    public AirTicket convert(UpdateAirTicketRequestDto source) {
        AirTicket airTicket = new AirTicket();
        airTicket.setPlace(source.getPlace());
        return airTicket;
    }
}
