package com.makichanov.core.util.converter;

import com.makichanov.core.converter.AirTicketToAirTicketDtoConverter;
import com.makichanov.core.converter.FlightAddressToFlightAddressDtoConverter;
import com.makichanov.core.converter.FlightDetailsToFlightDetailsDtoConverter;
import com.makichanov.core.converter.OrderToOrderDtoConverter;
import com.makichanov.core.model.response.AirTicketDto;
import com.makichanov.core.model.response.FlightAddressDto;
import com.makichanov.core.model.response.FlightDetailsDto;
import com.makichanov.core.model.response.OrderDto;
import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.entity.Order;

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
}
