package com.makichanov.core.repository;

import com.makichanov.core.model.entity.AirTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirTicketRepository extends JpaRepository<AirTicket, Long> {
}
