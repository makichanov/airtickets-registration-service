package com.makichanov.core.service;

import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.UpdateFlightDetailsRequestDto;

import java.util.List;

public interface FlightService {
    List<FlightDetails> findAll();

    FlightDetails find(Long id);

    FlightDetails create(CreateFlightDetailsRequestDto dto);

    FlightDetails update(Long id, UpdateFlightDetailsRequestDto dto);

    FlightDetails delete(Long id);
}
