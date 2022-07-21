package com.makichanov.core.service;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.repository.FlightAddressRepository;
import com.makichanov.core.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightsRepository;
    private final FlightAddressService flightAddressService;

    public List<FlightDetails> findAll(@Positive Long pageNum, @Positive Long pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum.intValue(), pageSize.intValue());
        return flightsRepository.findAll(pageRequest)
                .getContent();
    }

    public FlightDetails find(Long id) {
        return findById(id);
    }

    public FlightDetails findByRoute(Long flightAddressFromId, Long flightAddressToId) {
        FlightAddress from = flightAddressService.find(flightAddressFromId);
        FlightAddress to = flightAddressService.find(flightAddressToId);
        return flightsRepository.findByFlightFromAndFlightTo(from, to)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Flight not found from %s to %s", from, to)));
    }

    public FlightDetails create(FlightDetails flightDetails, Long flightAddressFromId, Long flightAddressToId) {
        FlightAddress from = flightAddressService.find(flightAddressFromId);
        FlightAddress to = flightAddressService.find(flightAddressToId);

        flightDetails.setFlightFrom(from);
        flightDetails.setFlightTo(to);
        return flightsRepository.save(flightDetails);
    }

    public FlightDetails update(Long id, FlightDetails updated, Long flightAddressFromId, Long flightAddressToId) {
        FlightDetails flightDetails = findById(id);
        FlightAddress flightAddressFrom = flightAddressService.find(flightAddressFromId);
        FlightAddress flightAddressTo = flightAddressService.find(flightAddressToId);

        flightDetails.setDepartureTime(updated.getDepartureTime());
        flightDetails.setArrivalTime(updated.getArrivalTime());
        flightDetails.setBasePrice(updated.getBasePrice());
        flightDetails.setMaxPlaces(updated.getMaxPlaces());
        flightDetails.setPlacesSold(updated.getPlacesSold());
        flightDetails.setFlightFrom(flightAddressFrom);
        flightDetails.setFlightTo(flightAddressTo);

        return flightDetails;
    }

    public void updateSoldPlaces(Long id, Integer newPlacesNumber) {
        flightsRepository.updateSoldPlaces(id, newPlacesNumber);
    }

    public FlightDetails delete(Long id) {
        FlightDetails flightDetails = findById(id);
        flightsRepository.delete(flightDetails);
        return flightDetails;
    }

    private FlightDetails findById(Long id) {
        return flightsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FlightDetails not found, requested id " + id));
    }
}
