package com.makichanov.core.service;

import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.entity.Order;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.factory.AirTicketFactory;
import com.makichanov.core.model.request.Route;
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
    private final AirTicketService ticketService;
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
    public Order create(Set<Route> routes) {
        List<AirTicket> airTickets = new ArrayList<>();

        // TODO: 7/28/22 Решение нуждается в рефакторинге.
        //  N^2 сложность не смущает?
        //  вот тут уже можно подумать о запросе в репу вместо хардкода.

        // внутренний цикл создает n объектов, как их создать без цикла?
        routes.forEach(r -> {
            FlightDetails flightDetails = flightService.findByRoute(r.getFlightFromId(), r.getFlightToId());

            int places = flightDetails.getPlaces();
            places -= r.getTicketsCount();
            flightDetails.setPlaces(places);

            for (int i = 0; i < r.getTicketsCount(); i++) {
                AirTicket airTicket = airTicketFactory.createAirTicket(flightDetails);
                airTickets.add(airTicket);
            }
        });
        Long totalPrice = countTotalPrice(airTickets);

        ticketService.createAll(airTickets);

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

    private Long countTotalPrice(List<AirTicket> airTickets) {
        return airTickets.stream()
                .mapToLong(AirTicket::getPrice)
                .sum();
    }
}
