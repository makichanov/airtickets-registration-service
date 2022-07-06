package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.Order;
import com.makichanov.core.repository.OrderReppsitory;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderReppsitory repository;
    private final ConversionService conversionService;

    @Override
    public OrderDto find(Long id) {
        Optional<Order> order = repository.findById(id);
        Order item = order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + id));
        return conversionService.convert(item, OrderDto.class);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = repository.findAll();
        return orders.stream()
                .map(o -> conversionService.convert(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = conversionService.convert(orderDto, Order.class);
        Order persisted = repository.save(order);
        return conversionService.convert(persisted, OrderDto.class);
    }

    @Override
    public OrderDto delete(Long deleteId) {
        Optional<Order> order = repository.findById(deleteId);
        Order item = order.orElseThrow(
                () -> new EntityNotFoundException("Order not found, requested id " + deleteId));
        repository.delete(item);
        return conversionService.convert(item, OrderDto.class);
    }
}
