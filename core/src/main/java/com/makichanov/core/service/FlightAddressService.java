package com.makichanov.core.service;

import com.makichanov.core.model.dto.CreatingFlightAddressDto;
import com.makichanov.core.model.entity.FlightAddress;

import java.util.List;

public interface FlightAddressService {
    List<FlightAddress> findAll();

    FlightAddress find(Long id);

    FlightAddress create(CreatingFlightAddressDto dto);

    FlightAddress delete(Long id);
}
