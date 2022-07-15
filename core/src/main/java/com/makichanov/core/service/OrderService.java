package com.makichanov.core.service;

import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.model.entity.Order;

import java.util.List;

public interface OrderService {
    Order find(Long id);

    List<Order> findAll();

    Order create(FlightAddress from, FlightAddress to, String username);

    Order delete(Long deleteId);
}
