package com.makichanov.core.service;

import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.entity.Order;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.factory.AirTicketFactory;
import com.makichanov.core.model.request.RouteDto;
import com.makichanov.core.repository.AirTicketRepository;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.repository.FlightRepository;
import com.makichanov.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AirTicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final FlightAddressRepository flightAddressRepository;
    private final FlightService flightService;
    private final AirTicketFactory airTicketFactory;

    public Order find(Long id) {
        return findById(id);
    }

    public List<Order> findAll(@Positive Long pageNum, @Positive Long pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum.intValue(), pageSize.intValue());
        return orderRepository.findAll(pageRequest)
                .getContent();
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order create(Set<RouteDto> routes) {
        List<AirTicket> airTickets = new ArrayList<>();

        for (RouteDto r : routes) {
            FlightDetails flightDetails = flightService.findByRoute(r.getFlightFromId(), r.getFlightToId());

            for (int i = 0; i < r.getTicketsCount(); i++) {
                flightDetails.incrementPlacesSold();

                AirTicket airTicket = airTicketFactory.createAirTicket(flightDetails);
                airTickets.add(airTicket);
            }

            flightRepository.updatePlacesSold(flightDetails.getPlacesSold(), flightDetails.getId());
        }
        Long totalPrice = countTotalPrice(airTickets);

        ticketRepository.saveAll(airTickets);

        Order order = new Order();
        order.setAirTickets(airTickets);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);

        return order;
    }

    private Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
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
