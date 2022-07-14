package com.makichanov.core.service;

import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.FlightAddress;

import java.util.List;

public interface OrderService {

    OrderDto find(Long id);

    List<OrderDto> findAll();

    OrderDto create(FlightAddress from, FlightAddress to, String username);

    OrderDto delete(Long deleteId);

}
