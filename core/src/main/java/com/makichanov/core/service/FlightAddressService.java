package com.makichanov.core.service;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.model.request.UpdateFlightAddressRequestDto;
import com.makichanov.core.repository.FlightAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightAddressService {
    private final FlightAddressRepository flightAddressRepository;

    public List<FlightAddress> findAll() {
        return flightAddressRepository.findAll();
    }

    public FlightAddress find(Long id) {
        return findById(id);
    }

    public FlightAddress create(FlightAddress flightAddress) {
        return flightAddressRepository.save(flightAddress);
    }

    @Transactional
    public FlightAddress update(Long id, FlightAddress updated) {
        // TODO converter
        FlightAddress flightAddress = findById(id);

        flightAddress.setAirportAddress(updated.getAirportAddress());
        flightAddress.setAirportName(updated.getAirportName());

        return flightAddress;
    }

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
