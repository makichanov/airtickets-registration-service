package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateFlightDetailsRequest;
import com.makichanov.core.model.request.UpdateFlightDetailsRequest;
import com.makichanov.core.model.response.FlightDetailsDto;
import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.service.FlightService;
import com.makichanov.core.util.converter.ConversionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Flight controller", description = "CRUD operations for FlightDetails entity")
public class FlightController {
    private final FlightService flightService;
    private final ConversionService conversionService;

    @GetMapping
    @Operation(summary = "Read all flights", description = "Returns all flights by page and page size")
    public ResponseEntity<List<FlightDetailsDto>> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<FlightDetails> flightDetails = flightService.findAll(pageNum, pageSize);

        return new ResponseEntity<>(ConversionUtils.toFlightDetailsDtoList(flightDetails), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read flight by id", description = "Returns found flight entity")
    public ResponseEntity<FlightDetailsDto> read(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.find(id);

        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create flight", description = "Returns created flight entity")
    public ResponseEntity<FlightDetailsDto> create(@RequestBody @Valid CreateFlightDetailsRequest dto) {
        FlightDetails flightDetails = conversionService.convert(dto, FlightDetails.class);

        FlightDetails created = flightService.create(flightDetails, dto.getFlightFromId(), dto.getFlightToId());

        return new ResponseEntity<>(conversionService.convert(created, FlightDetailsDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update flight by id", description = "Updates flight by id, returns updated flight entity")
    public ResponseEntity<FlightDetailsDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateFlightDetailsRequest dto) {
        FlightDetails flightDetails = conversionService.convert(dto, FlightDetails.class);

        FlightDetails updated = flightService.update(id, flightDetails, dto.getFlightFromId(), dto.getFlightToId());

        return new ResponseEntity<>(conversionService.convert(updated, FlightDetailsDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete flight by id", description = "Deletes flight by id, returns deleted flight entity")
    public ResponseEntity<FlightDetailsDto> delete(@PathVariable Long id) {
        FlightDetails flightDetails = flightService.delete(id);

        return new ResponseEntity<>(conversionService.convert(flightDetails, FlightDetailsDto.class), HttpStatus.OK);
    }
}
