package com.makichanov.core.service;

import com.makichanov.core.entity.Order;
import com.makichanov.core.model.request.CreateOrderRequestDto;

import java.util.List;

public interface OrderService {
    Order find(Long id);

    List<Order> findAll();

    List<Order> findByUserId(Long userId);

    Order create(CreateOrderRequestDto dto);

    Order delete(Long deleteId);
}
