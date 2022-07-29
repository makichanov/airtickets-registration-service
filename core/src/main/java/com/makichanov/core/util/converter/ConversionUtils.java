package com.makichanov.core.util.converter;

import com.makichanov.core.converter.*;
import com.makichanov.core.entity.*;
import com.makichanov.core.model.response.*;

import java.util.List;
import java.util.stream.Collectors;


// TODO: 7/28/22 этот функционал можно было бы включить в сами конвертеры либо применить наследование и сделать абстрактный класс для конвертации, а дальше переоперделять.
//  По итогу при расширении проекта перерастет в очень большой класс. SOLID - SO.
public final class ConversionUtils {
    // TODO: 7/28/22 @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private ConversionUtils() {}

    // TODO: 7/28/22 логические блоки разделяем пустыми строками
    public static List<AirTicketDto> toAirTicketDtoList(List<AirTicket> airTickets) {
        var converter = new AirTicketToAirTicketDtoConverter();
        return airTickets.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public static List<FlightAddressDto> toFlightAddressDtoList(List<FlightAddress> flightAddresses) {
        var converter = new FlightAddressToFlightAddressDtoConverter();
        return flightAddresses.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public static List<FlightDetailsDto> toFlightDetailsDtoList(List<FlightDetails> flightDetails) {
        var converter = new FlightDetailsToFlightDetailsDtoConverter();
        return flightDetails.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public static List<OrderDto> toOrdersDtoList(List<Order> orders) {
        var converter = new OrderToOrderDtoConverter();
        return orders.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        var converter = new UserToUserDtoConverter();
        return users.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
