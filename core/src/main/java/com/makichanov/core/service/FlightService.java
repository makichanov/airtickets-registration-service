package com.makichanov.core.service;

import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.entity.FlightDetails;

import java.util.List;

public interface FlightService {
    List<FlightDetails> findAll();

    FlightDetails find(Long id);

    FlightDetails create(CreateFlightDetailsRequestDto dto);

    FlightDetails delete(Long id);
}
