package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateFlightDetailsRequestDto;
import com.makichanov.core.model.request.UpdateFlightDetailsRequestDto;
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

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<FlightDetailsDto>> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<FlightDetails> flightDetails = flightService.findAll(pageNum, pageSize);

        return new ResponseEntity<>(ConversionUtils.toFlightDetailsDtoList(flightDetails), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDetailsDto> read(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.find(id);

        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightDetailsDto> create(@RequestBody @Valid CreateFlightDetailsRequestDto dto) {
        FlightDetails flightDetails = conversionService.convert(dto, FlightDetails.class);

        FlightDetails created = flightService.create(flightDetails, dto.getFlightFromId(), dto.getFlightToId());

        return new ResponseEntity<>(conversionService.convert(created, FlightDetailsDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FlightDetailsDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateFlightDetailsRequestDto dto) {
        FlightDetails flightDetails = conversionService.convert(dto, FlightDetails.class);

        FlightDetails updated = flightService.update(id, flightDetails, dto.getFlightFromId(), dto.getFlightToId());

        return new ResponseEntity<>(conversionService.convert(updated, FlightDetailsDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDetailsDto> delete(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.delete(id);

        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }
}
