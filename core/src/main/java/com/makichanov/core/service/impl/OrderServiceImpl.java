package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.AirTicket;
import com.makichanov.core.model.entity.Order;
import com.makichanov.core.model.entity.User;
import com.makichanov.core.repository.AirTicketRepository;
import com.makichanov.core.repository.OrderRepository;
import com.makichanov.core.repository.UserRepository;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AirTicketRepository ticketRepository;
    private final ConversionService conversionService;
    private final UserRepository userRepository;

    @Override
    public OrderDto find(Long id) {
        Order order = findOrder(id);
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(o -> conversionService.convert(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto create(List<Long> ticketsIds, Long userId) {
        List<AirTicket> airTickets = new ArrayList<>();
        for (Long ticketId : ticketsIds) {
            AirTicket o = findAirTicket(ticketId);
            airTickets.add(o);
        }
        Long price = countTotalPrice(airTickets);
        User user = findUser(userId);
        Order order = new Order();
        order.setAirTickets(airTickets);
        order.setTotalPrice(price);
        order.setUser(user);
        Order persisted = orderRepository.save(order);
        return conversionService.convert(persisted, OrderDto.class);
    }

    @Override
    public OrderDto delete(Long deleteId) {
        Order order = findOrder(deleteId);
        orderRepository.delete(order);
        return conversionService.convert(order, OrderDto.class);
    }

    private Order findOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
    }

    private AirTicket findAirTicket(Long id) {
        Optional<AirTicket> order = ticketRepository.findById(id);
        return order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
    }

    private User findUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(
                () -> new EntityNotFoundException("User not found, requested id " + userId));
    }

    private Long countTotalPrice(List<AirTicket> airTickets) {
        return airTickets.stream()
                .map(AirTicket::getPrice)
                .count();
    }

}
