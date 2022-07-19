package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.model.response.FlightAddressDto;
import com.makichanov.core.entity.FlightAddress;
import com.makichanov.core.service.FlightAddressService;
import com.makichanov.core.util.converter.ConversionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/destinations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FlightAddressController {
    private final FlightAddressService flightAddressService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<FlightAddressDto>> read() {
        List<FlightAddress> flightAddresses = flightAddressService.findAll();

        return new ResponseEntity<>(ConversionUtils.toFlightAddressDtoList(flightAddresses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightAddressDto> read(@PathVariable Long id) {
        FlightAddress flightAddress = flightAddressService.find(id);
        return new ResponseEntity<>(conversionService.convert(flightAddress, FlightAddressDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightAddressDto> create(@RequestBody CreateFlightAddressRequestDto dto) {
        FlightAddress flightAddress = flightAddressService.create(dto);
        return new ResponseEntity<>(conversionService.convert(flightAddress, FlightAddressDto.class), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightAddressDto> delete(@PathVariable Long id) {
        FlightAddress flightAddress = flightAddressService.delete(id);
        return new ResponseEntity<>(conversionService.convert(flightAddress, FlightAddressDto.class), HttpStatus.OK);

    }
}
