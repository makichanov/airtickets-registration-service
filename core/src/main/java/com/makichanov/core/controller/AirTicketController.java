package com.makichanov.core.controller;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.model.entity.AirTicket;
import com.makichanov.core.service.AirTicketService;
import com.makichanov.core.util.converter.ConversionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Airtickets controller", description = "CRUD airtickets operations")
@RequiredArgsConstructor
public class AirTicketController {
    private final AirTicketService airTicketService;
    private final ConversionService conversionService;
    private final ConversionUtils conversionUtils;
//TODO: Рекомендую оборачивать возрващаемые значения в ResponseEntity
// либо оборачивать в свою кастомную обертку через @ControllerAdvice, например

    @GetMapping
    @Operation(summary = "Read all tickets", description = "Returns all tickets from database")
    public ResponseEntity<List<AirTicketDto>> read() {
        List<AirTicket> airTickets = airTicketService.findAll();

        return new ResponseEntity<>(conversionUtils.toAirTicketDtoList(airTickets), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read ticket by id", description = """
            Returns ticket with requested id, throws EntityNotFoundException if ticket was not found
            """)
    public ResponseEntity<AirTicketDto> read(@PathVariable Long id) {
        AirTicket airTicket = airTicketService.find(id);

        return new ResponseEntity<>(conversionService.convert(airTicket, AirTicketDto.class), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create airticket", description = """
            Creates ticket in database, returns created ticket
            """)
    public ResponseEntity<AirTicketDto> create(@RequestBody CreatingAirTicketDto dto) {
        AirTicket airTicket = airTicketService.create(dto);

        return new ResponseEntity<>(conversionService.convert(airTicket, AirTicketDto.class), HttpStatus.CREATED);
    }


    //TODO: Где функционал по обновлению билетов?
    // Will be soon
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete airticket by id", description = """
            Deletes ticket from database, returns deleted ticket
            """)
    public ResponseEntity<AirTicketDto> delete(@PathVariable Long id) {
        AirTicket airTicket = airTicketService.delete(id);

        return new ResponseEntity<>(conversionService.convert(airTicket, AirTicketDto.class), HttpStatus.OK);
    }
}
