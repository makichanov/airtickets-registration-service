package com.makichanov.core.repository;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightDetails, Long> {
    Optional<FlightDetails> findByFlightFromAndFlightTo(FlightAddress from, FlightAddress to);
}
