package com.makichanov.core.repository;

import com.makichanov.core.entity.FlightAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightAddressRepository extends JpaRepository<FlightAddress, Long> {
}
