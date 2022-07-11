package com.makichanov.core.converter;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.Order;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {

    @Override
    public OrderDto convert(Order source) {
        UserToUserDtoConverter userDtoConverter = new UserToUserDtoConverter();
        AirTicketToAirTicketDtoConverter airTicketDtoConverter = new AirTicketToAirTicketDtoConverter();
        List<AirTicketDto> airTickets = source.getAirTickets()
                .stream()
                .map(airTicketDtoConverter::convert)
                .collect(Collectors.toList());
        return OrderDto.builder()
                .id(source.getId())
                .totalPrice((double) source.getTotalPrice() / 100)
                .createDate(source.getCreateDate())
                .userId(source.getUser().getId())
                .airTickets(airTickets)
                .build();
    }

}
