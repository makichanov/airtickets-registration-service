package com.makichanov.core.repository;

import com.makichanov.core.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReppsitory extends JpaRepository<Order, Long> {
}
