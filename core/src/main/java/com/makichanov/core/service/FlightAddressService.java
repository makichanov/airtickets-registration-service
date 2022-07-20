package com.makichanov.core.service;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.repository.FlightAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Positive;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightAddressService {
    private final FlightAddressRepository flightAddressRepository;

    public List<FlightAddress> findAll(@Positive Long pageNum, @Positive Long pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum.intValue(), pageSize.intValue());
        return flightAddressRepository.findAll(pageRequest)
                .getContent();
    }

    public FlightAddress find(Long id) {
        return findById(id);
    }

    public FlightAddress create(FlightAddress flightAddress) {
        return flightAddressRepository.save(flightAddress);
    }

    @Transactional
    public FlightAddress update(Long id, FlightAddress updated) {
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
