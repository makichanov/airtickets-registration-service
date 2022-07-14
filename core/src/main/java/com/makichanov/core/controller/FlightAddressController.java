package com.makichanov.core.controller;

import com.makichanov.core.model.dto.CreatingFlightAddressDto;
import com.makichanov.core.model.dto.FlightAddressDto;
import com.makichanov.core.service.FlightAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
@RequiredArgsConstructor
public class FlightAddressController {
    private final FlightAddressService flightAddressService;

    @GetMapping
    public List<FlightAddressDto> read() {
        return flightAddressService.findAll();
    }

    @GetMapping("/{id}")
    public FlightAddressDto read(@PathVariable Long id) {
        return flightAddressService.findById(id);
    }

    @PostMapping
    public FlightAddressDto create(@RequestBody CreatingFlightAddressDto dto) {
        return flightAddressService.create(dto);
    }

    @DeleteMapping("/{id}")
    public FlightAddressDto delete(@PathVariable Long id) {
        return flightAddressService.delete(id);
    }
}
