package com.makichanov.core.repository;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightDetails, Long> {

    Optional<FlightDetails> findByFlightFromAndFlightTo(FlightAddress from, FlightAddress to);

    // TODO: 7/28/22 для такой операции делать query -- too much
    @Query("update FlightDetails set placesSold = ?2 where id = ?1")
    @Modifying
    void updateSoldPlaces(Long id, Integer newPlacesNumber);

}
