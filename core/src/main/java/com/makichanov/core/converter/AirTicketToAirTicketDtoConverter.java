package com.makichanov.core.converter;

import com.makichanov.core.model.response.AirTicketDto;
import com.makichanov.core.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

//TODO: Настоятельно рекомендую использовать MapStruct
//TODO: Лучше тут поставь @Component.Создание бина через метод конфига - вариант, но не очевидно
// TODO: 7/21/22 Не вижу исправлений.
public class AirTicketToAirTicketDtoConverter implements Converter<AirTicket, AirTicketDto> {

    @Override
    public AirTicketDto convert(AirTicket source) {
        FlightDetailsToFlightDetailsDtoConverter converter =
                new FlightDetailsToFlightDetailsDtoConverter();
        return AirTicketDto.builder()
                .id(source.getId())
                .price((double) source.getPrice() / 100)
                .place(source.getPlace())
                .flightDetails(converter.convert(source.getFlightDetails()))
                .build();
    }

}
