package com.makichanov.core.controller;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.service.AirTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Airtickets controller", description = "CRUD airtickets operations")
@RequiredArgsConstructor
public class AirTicketController {
    private final AirTicketService airTicketService;
//TODO: Рекомендую оборачивать возрващаемые значения в ResponseEntity
// либо оборачивать в свою кастомную обертку через @ControllerAdvice, например
    @GetMapping
    @Operation(summary = "Read all tickets", description = "Returns all tickets from database")
    public List<AirTicketDto> read() {
        return airTicketService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read ticket by id", description = """
            Returns ticket with requested id, throws EntityNotFoundException if ticket was not found
            """)
    public AirTicketDto read(@PathVariable Long id) {
        return airTicketService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create airticket", description = """
            Creates ticket in database, returns created ticket
            """)
    public AirTicketDto create(@RequestBody CreatingAirTicketDto dto) {
        return airTicketService.create(dto);
    }


    //TODO: Где функционал по обновлению билетов?
    // Will be soon
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete airticket by id", description = """
            Deletes ticket from database, returns deleted ticket
            """)
    public AirTicketDto delete(@PathVariable Long id) {
        return airTicketService.delete(id);
    }
}
