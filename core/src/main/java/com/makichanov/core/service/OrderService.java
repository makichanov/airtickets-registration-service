package com.makichanov.core.service;

import com.makichanov.core.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto find(Long id);

    List<OrderDto> findAll();

    OrderDto create(List<Long> ticketsIds, Long userId);

    OrderDto delete(Long deleteId);

}
