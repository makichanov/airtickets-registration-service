package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.model.response.FlightDetailsDto;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.service.FlightService;
import com.makichanov.core.util.converter.ConversionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final ConversionService conversionService;
    private final ConversionUtils conversionUtils;

    @GetMapping
    public ResponseEntity<List<FlightDetailsDto>> read() {
        List<FlightDetails> flightDetails = flightService.findAll();
        return new ResponseEntity<>(conversionUtils.toFlightDetailsDtoList(flightDetails), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDetailsDto> read(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.find(id);
        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightDetailsDto> create(@RequestBody CreateFlightDetailsRequestDto dto) {
        FlightDetails flightDetails = flightService.create(dto);
        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDetailsDto> delete(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.delete(id);
        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }
}
