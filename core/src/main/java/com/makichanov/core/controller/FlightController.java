package com.makichanov.core.controller;

import com.makichanov.core.model.dto.CreatingFlightDetailsDto;
import com.makichanov.core.model.dto.FlightDetailsDto;
import com.makichanov.core.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public List<FlightDetailsDto> read() {
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    public FlightDetailsDto read(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PostMapping
    public FlightDetailsDto create(@RequestBody CreatingFlightDetailsDto dto) {
        return flightService.create(dto);
    }

    @DeleteMapping("/{id}")
    public FlightDetailsDto delete(@PathVariable Long id) {
        return flightService.delete(id);
    }
}
