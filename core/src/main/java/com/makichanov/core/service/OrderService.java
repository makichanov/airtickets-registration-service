package com.makichanov.core.service;

import com.makichanov.core.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto find(Long id);

    List<OrderDto> findAll();

    OrderDto create(OrderDto userDto);

    OrderDto delete(Long deleteId);

}
