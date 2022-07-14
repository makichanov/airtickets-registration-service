package com.makichanov.core.service;

import com.makichanov.core.model.dto.CreatingFlightAddressDto;
import com.makichanov.core.model.dto.FlightAddressDto;

import java.util.List;

public interface FlightAddressService {

    List<FlightAddressDto> findAll();

    FlightAddressDto findById(Long id);

    FlightAddressDto create(CreatingFlightAddressDto dto);

    FlightAddressDto delete(Long id);

}
