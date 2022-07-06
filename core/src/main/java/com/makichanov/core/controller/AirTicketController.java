package com.makichanov.core.controller;

import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.service.AirTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AirTicketController {

    private final AirTicketService airTicketService;

    @GetMapping
    public List<AirTicketDto> read() {
        return airTicketService.findAll();
    }

    @GetMapping("/{id}")
    public AirTicketDto read(@PathVariable Long id) {
        return airTicketService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirTicketDto create(@RequestBody CreatingAirTicketDto dto) {
        return airTicketService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirTicketDto delete(@PathVariable Long id) {
        return airTicketService.delete(id);
    }

}
