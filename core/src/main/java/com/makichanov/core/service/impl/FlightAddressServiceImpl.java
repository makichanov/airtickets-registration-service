package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.CreatingFlightAddressDto;
import com.makichanov.core.model.dto.FlightAddressDto;
import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.service.FlightAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightAddressServiceImpl implements FlightAddressService {
    private final FlightAddressRepository flightAddressRepository;
    private final ConversionService conversionService;

    @Override
    public List<FlightAddressDto> findAll() {
        List<FlightAddress> flightAddresses = flightAddressRepository.findAll();
        return flightAddresses.stream()
                .map(a-> conversionService.convert(a, FlightAddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightAddressDto findById(Long id) {
        FlightAddress flightAddress = findFlightAddress(id);
        return conversionService.convert(flightAddress, FlightAddressDto.class);
    }

    @Override
    public FlightAddressDto create(CreatingFlightAddressDto dto) {
        FlightAddress flightAddress = conversionService.convert(dto, FlightAddress.class);
        FlightAddress persisted = flightAddressRepository.save(flightAddress);
        return conversionService.convert(flightAddress, FlightAddressDto.class);
    }

    @Override
    public FlightAddressDto delete(Long id) {
        FlightAddress flightAddress = findFlightAddress(id);
        flightAddressRepository.delete(flightAddress);
        return conversionService.convert(flightAddress, FlightAddressDto.class);
    }

    private FlightAddress findFlightAddress(Long id) {
        return flightAddressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FlightAddress not found, requested id " + id));
    }
}
