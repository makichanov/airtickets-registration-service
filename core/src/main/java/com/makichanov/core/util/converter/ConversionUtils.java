package com.makichanov.core.util.converter;

import com.makichanov.core.converter.*;
import com.makichanov.core.entity.*;
import com.makichanov.core.model.response.*;

import java.util.List;
import java.util.stream.Collectors;

public final class ConversionUtils {

    private ConversionUtils() {}

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
