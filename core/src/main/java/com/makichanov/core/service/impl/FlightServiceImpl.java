package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.CreatingFlightDetailsDto;
import com.makichanov.core.model.dto.FlightDetailsDto;
import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.model.entity.FlightDetails;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.repository.FlightRepository;
import com.makichanov.core.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightsRepository;
    private final FlightAddressRepository flightAddressRepository;
    private final ConversionService conversionService;

    @Override
    public List<FlightDetailsDto> findAll() {
        List<FlightDetails> flightDetails = flightsRepository.findAll();
        return flightDetails.stream()
                .map(d -> conversionService.convert(d, FlightDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDetailsDto findById(Long id) {
        FlightDetails flightDetails = findFlightDetails(id);
        return conversionService.convert(flightDetails, FlightDetailsDto.class);
    }

    @Override
    public FlightDetailsDto create(CreatingFlightDetailsDto dto) {
        FlightDetails flightDetails = conversionService.convert(dto, FlightDetails.class);
        FlightAddress from = findFlightAddress(dto.getFlightFromId());
        FlightAddress to = findFlightAddress(dto.getFlightToId());
        flightDetails.setFlightFrom(from);
        flightDetails.setFlightTo(to);
        FlightDetails persisted = flightsRepository.save(flightDetails);
        return conversionService.convert(persisted, FlightDetailsDto.class);
    }

    @Override
    public FlightDetailsDto delete(Long id) {
        FlightDetails flightDetails = findFlightDetails(id);
        flightsRepository.delete(flightDetails);
        return conversionService.convert(flightDetails, FlightDetailsDto.class);
    }

    private FlightDetails findFlightDetails(Long id) {
        Optional<FlightDetails> flightDetails = flightsRepository.findById(id);
        return flightDetails.orElseThrow(
                () -> new EntityNotFoundException("FlightDetails not found, requested id " + id));
    }

    private FlightAddress findFlightAddress(Long id) {
        Optional<FlightAddress> flightAddress = flightAddressRepository.findById(id);
        return flightAddress.orElseThrow(
                () -> new EntityNotFoundException("FlightDetails not found, requested id " + id));
    }
}
