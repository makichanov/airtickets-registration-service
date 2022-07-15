package com.makichanov.core.service;

import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.entity.FlightAddress;

import java.util.List;

public interface FlightAddressService {
    List<FlightAddress> findAll();

    FlightAddress find(Long id);

    FlightAddress create(CreateFlightAddressRequestDto dto);

    FlightAddress delete(Long id);
}
