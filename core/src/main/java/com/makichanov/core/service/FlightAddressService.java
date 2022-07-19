package com.makichanov.core.service;

import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.model.request.UpdateFlightAddressRequestDto;

import java.util.List;

public interface FlightAddressService {
    List<FlightAddress> findAll();

    FlightAddress find(Long id);

    FlightAddress create(CreateFlightAddressRequestDto dto);

    FlightAddress update(Long id, UpdateFlightAddressRequestDto dto);

    FlightAddress delete(Long id);
}
