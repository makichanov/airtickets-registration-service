package com.makichanov.core.repository;

import com.makichanov.core.model.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightDetails, Long> {
}