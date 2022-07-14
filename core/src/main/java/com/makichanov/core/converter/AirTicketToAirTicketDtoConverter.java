package com.makichanov.core.converter;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.entity.AirTicket;
import org.springframework.core.convert.converter.Converter;

//TODO: Настоятельно рекомендую использовать MapStruct
//TODO: Лучше тут поставь @Component.Создание бина через метод конфига - вариант, но не очевидно
public class AirTicketToAirTicketDtoConverter implements Converter<AirTicket, AirTicketDto> {

    @Override
    public AirTicketDto convert(AirTicket source) {
        FlightAddressToFlightAddressDtoConverter flightAddressDtoConverter =
                new FlightAddressToFlightAddressDtoConverter();
        return AirTicketDto.builder()
                .id(source.getId())
                .routeName(source.getRouteName())
                .price((double) source.getPrice() / 100)
                .quantity(source.getQuantity())
                .flightTimeSeconds(source.getFlightTimeSeconds())
                .departureTime(source.getDepartureTime())
                .arrivalTime(source.getArrivalTime())
                .addressFrom(flightAddressDtoConverter.convert(source.getAddressFrom()))
                .addressTo(flightAddressDtoConverter.convert(source.getAddressTo()))
                .build();
    }

}
