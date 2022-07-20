package com.makichanov.core.service;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.UpdateFlightDetailsRequestDto;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightsRepository;
    private final FlightAddressRepository flightAddressRepository;

    public List<FlightDetails> findAll() {
        return flightsRepository.findAll();
    }

    public FlightDetails find(Long id) {
        return findById(id);
    }

    public FlightDetails create(FlightDetails flightDetails, Long flightAddressFromId, Long flightAddressToId) {
        FlightAddress from = findFlightAddress(flightAddressFromId);
        FlightAddress to = findFlightAddress(flightAddressToId);

        flightDetails.setFlightFrom(from);
        flightDetails.setFlightTo(to);
        return flightsRepository.save(flightDetails);
    }

    public FlightDetails update(Long id, FlightDetails updated, Long flightAddressFromId, Long flightAddressToId) {
        FlightDetails flightDetails = findById(id);
        FlightAddress flightAddressFrom = findFlightAddress(flightAddressFromId);
        FlightAddress flightAddressTo = findFlightAddress(flightAddressToId);

        flightDetails.setDepartureTime(updated.getDepartureTime());
        flightDetails.setArrivalTime(updated.getArrivalTime());
        flightDetails.setBasePrice(updated.getBasePrice());
        flightDetails.setMaxPlaces(updated.getMaxPlaces());
        flightDetails.setPlacesSold(updated.getPlacesSold());
        flightDetails.setFlightFrom(flightAddressFrom);
        flightDetails.setFlightTo(flightAddressTo);

        return flightDetails;
    }

    public FlightDetails delete(Long id) {
        FlightDetails flightDetails = findById(id);
        flightsRepository.delete(flightDetails);
        return flightDetails;
    }

    private FlightDetails findById(Long id) {
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
