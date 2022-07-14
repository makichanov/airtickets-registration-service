package com.makichanov.core.service;

import com.makichanov.core.model.dto.CreatingFlightDetailsDto;
import com.makichanov.core.model.dto.FlightDetailsDto;

import java.util.List;

public interface FlightService {

    List<FlightDetailsDto> findAll();

    FlightDetailsDto findById(Long id);

    FlightDetailsDto create(CreatingFlightDetailsDto dto);

    FlightDetailsDto delete(Long id);

}
