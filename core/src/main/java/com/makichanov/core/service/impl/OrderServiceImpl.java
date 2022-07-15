package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.Order;
import com.makichanov.core.entity.User;
import com.makichanov.core.repository.AirTicketRepository;
import com.makichanov.core.repository.OrderRepository;
import com.makichanov.core.repository.UserRepository;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AirTicketRepository ticketRepository;
    private final ConversionService conversionService;
    private final UserRepository userRepository;

    @Override
    public Order find(Long id) {
        return findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override // FIXME
    public Order create(FlightAddress from, FlightAddress to, String username) {
        List<AirTicket> airTickets = new ArrayList<>();
        Long price = countTotalPrice(airTickets);
        Order order = new Order();
        order.setAirTickets(airTickets);
        order.setTotalPrice(price);
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
        return order;
    }

    private Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
    }

    private AirTicket findAirTicket(Long id) {
        Optional<AirTicket> order = ticketRepository.findById(id);
        return order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
    }

    private Long countTotalPrice(List<AirTicket> airTickets) {
        return airTickets.stream()
                .map(AirTicket::getPrice)
                .count();
    }
}
