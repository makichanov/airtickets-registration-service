package com.makichanov.core.converter;

import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.dto.UserDto;
import com.makichanov.core.model.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

//TODO: Смотри TODO в других конвертерах
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        OrderToOrderDtoConverter converter = new OrderToOrderDtoConverter();
        List<OrderDto> orders = source.getOrders()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getPassword())
                .balance((double) source.getBalance() / 100)
                .orders(orders)
                .build();
    }

}
