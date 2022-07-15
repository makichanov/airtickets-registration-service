package com.makichanov.core.service;

import com.makichanov.core.model.dto.CreatingFlightDetailsDto;
import com.makichanov.core.model.entity.FlightDetails;

import java.util.List;

public interface FlightService {
    List<FlightDetails> findAll();

    FlightDetails find(Long id);

    FlightDetails create(CreatingFlightDetailsDto dto);

    FlightDetails delete(Long id);
}
