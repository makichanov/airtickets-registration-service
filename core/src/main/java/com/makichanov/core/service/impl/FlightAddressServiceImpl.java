package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.model.request.UpdateFlightAddressRequestDto;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.service.FlightAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightAddressServiceImpl implements FlightAddressService {
    private final FlightAddressRepository flightAddressRepository;
    private final ConversionService conversionService;

    @Override
    public List<FlightAddress> findAll() {
        return flightAddressRepository.findAll();
    }

    @Override
    public FlightAddress find(Long id) {
        return findById(id);
    }

    @Override
    public FlightAddress create(CreateFlightAddressRequestDto dto) {
        FlightAddress flightAddress = conversionService.convert(dto, FlightAddress.class);

        return flightAddressRepository.save(flightAddress);
    }

    @Override
    @Transactional
    public FlightAddress update(Long id, UpdateFlightAddressRequestDto dto) {
        FlightAddress flightAddress = findById(id);

        flightAddress.setAirportAddress(dto.getAirportAddress());
        flightAddress.setAirportName(dto.getAirportName());

        return flightAddress;
    }

    @Override
    public FlightAddress delete(Long id) {
        FlightAddress flightAddress = findById(id);

        flightAddressRepository.delete(flightAddress);

        return flightAddress;
    }

    private FlightAddress findById(Long id) {
        return flightAddressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FlightAddress not found, requested id " + id));
    }
}
