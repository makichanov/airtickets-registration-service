package com.makichanov.core.controller;

import com.makichanov.core.model.request.UpdateAirTicketRequestDto;
import com.makichanov.core.model.response.AirTicketDto;
import com.makichanov.core.model.request.CreateAirTicketRequestDto;
import com.makichanov.core.entity.AirTicket;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Airtickets controller", description = "CRUD airtickets operations")
@RequiredArgsConstructor
public class AirTicketController {
    private final AirTicketService airTicketService;
    private final ConversionService conversionService;

    @GetMapping
    @Operation(summary = "Read all tickets", description = "Returns all tickets from database")
    public ResponseEntity<List<AirTicketDto>> readAll() {
        List<AirTicket> airTickets = airTicketService.findAll();

        return new ResponseEntity<>(ConversionUtils.toAirTicketDtoList(airTickets), HttpStatus.OK);
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
    public ResponseEntity<AirTicketDto> create(@RequestBody @Valid CreateAirTicketRequestDto dto) {
        AirTicket airTicket = conversionService.convert(dto, AirTicket.class);

        AirTicket created = airTicketService.create(airTicket);

        return new ResponseEntity<>(conversionService.convert(created, AirTicketDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AirTicketDto> update(@PathVariable Long id,
                                               @RequestBody @Valid UpdateAirTicketRequestDto dto) {
        AirTicket airTicket = conversionService.convert(dto, AirTicket.class);
        AirTicket updated = airTicketService.update(id, airTicket);

        return new ResponseEntity<>(conversionService.convert(updated, AirTicketDto.class), HttpStatus.OK);
    }

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
