package com.makichanov.core.util.converter;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.FlightAddressDto;
import com.makichanov.core.model.dto.FlightDetailsDto;
import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.AirTicket;
import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.model.entity.FlightDetails;
import com.makichanov.core.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class ConversionUtils {
    private final ConversionService conversionService;

    public List<AirTicketDto> toAirTicketDtoList(List<AirTicket> airTickets) {
        return airTickets.stream()
                .map(t -> conversionService.convert(t, AirTicketDto.class))
                .collect(Collectors.toList());
    }

    public List<FlightAddressDto> toFlightAddressDtoList(List<FlightAddress> flightAddresses) {
        return flightAddresses.stream()
                .map(a -> conversionService.convert(a, FlightAddressDto.class))
                .collect(Collectors.toList());
    }

    public List<FlightDetailsDto> toFlightDetailsDtoList(List<FlightDetails> flightDetails) {
        return flightDetails.stream()
                .map(d -> conversionService.convert(d, FlightDetailsDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> toOrdersDtoList(List<Order> orders) {
        return orders.stream()
                .map(o -> conversionService.convert(o, OrderDto.class))
                .collect(Collectors.toList());
    }
}
