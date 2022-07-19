package com.makichanov.core.repository;

import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightDetails, Long> {

    Optional<FlightDetails> findByFlightFromAndFlightTo(FlightAddress from, FlightAddress to);

    @Query("update FlightDetails set placesSold = ?1 where id = ?2")
    @Modifying
    void updatePlacesSold(Integer newPlacesNumber, Long id);

}
