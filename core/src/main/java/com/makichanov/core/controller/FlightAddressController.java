package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateFlightAddressRequestDto;
import com.makichanov.core.model.request.UpdateFlightAddressRequestDto;
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

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/destinations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FlightAddressController {
    private final FlightAddressService flightAddressService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<FlightAddressDto>> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<FlightAddress> flightAddresses = flightAddressService.findAll(pageNum, pageSize);

        return new ResponseEntity<>(ConversionUtils.toFlightAddressDtoList(flightAddresses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightAddressDto> read(@PathVariable Long id) {
        FlightAddress flightAddress = flightAddressService.find(id);

        return new ResponseEntity<>(conversionService.convert(flightAddress, FlightAddressDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightAddressDto> create(@RequestBody @Valid CreateFlightAddressRequestDto dto) {
        FlightAddress flightAddress = conversionService.convert(dto, FlightAddress.class);

        FlightAddress created = flightAddressService.create(flightAddress);

        return new ResponseEntity<>(conversionService.convert(created, FlightAddressDto.class), HttpStatus.CREATED);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<FlightAddressDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateFlightAddressRequestDto dto) {
        FlightAddress flightAddress = conversionService.convert(dto, FlightAddress.class);

        FlightAddress updated = flightAddressService.update(id, flightAddress);

        return new ResponseEntity<>(conversionService.convert(updated, FlightAddressDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightAddressDto> delete(@PathVariable Long id) {
        FlightAddress flightAddress = flightAddressService.delete(id);

        return new ResponseEntity<>(conversionService.convert(flightAddress, FlightAddressDto.class), HttpStatus.OK);

    }
}
