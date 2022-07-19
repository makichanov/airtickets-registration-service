package com.makichanov.core.service.impl;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.Order;
import com.makichanov.core.factory.AirTicketFactory;
import com.makichanov.core.model.request.CreateOrderRequestDto;
import com.makichanov.core.model.request.RouteDto;
import com.makichanov.core.repository.*;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AirTicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final FlightAddressRepository flightAddressRepository;
    private final AirTicketFactory airTicketFactory;

    @Override
    public Order find(Long id) {
        return findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Order create(CreateOrderRequestDto dto) {
        List<AirTicket> airTickets = new ArrayList<>();

        for (RouteDto r : dto.getRoutes()) {
            FlightAddress from = findFlightAddressById(r.getFlightFromId());
            FlightAddress to = findFlightAddressById(r.getFlightToId());
            FlightDetails flightDetails = findFlightByRoute(from, to);

            if (flightDetails.getPlacesSold().equals(flightDetails.getMaxPlaces())) {
                // TODO tickets count validator
            }

            flightDetails.incrementPlacesSold();

            AirTicket airTicket = airTicketFactory.createAirTicket(flightDetails);
            airTickets.add(airTicket);

            flightRepository.updatePlacesSold(flightDetails.getPlacesSold(), flightDetails.getId());
        }

        Long totalPrice = countTotalPrice(airTickets);

        ticketRepository.saveAll(airTickets);

        Order order = new Order();
        order.setAirTickets(airTickets);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);

        return order;
    }

    private Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
    }

    private FlightDetails findFlightByRoute(FlightAddress from, FlightAddress to) {
        return flightRepository.findByFlightFromAndFlightTo(from, to)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Flight not found from %s to %s", from, to)));
    }

    private FlightAddress findFlightAddressById(Long id) {
        return flightAddressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight address not found, id " + id));
    }

    private Long countTotalPrice(List<AirTicket> airTickets) {
        return airTickets.stream()
                .mapToLong(AirTicket::getPrice)
                .sum();
    }
}
