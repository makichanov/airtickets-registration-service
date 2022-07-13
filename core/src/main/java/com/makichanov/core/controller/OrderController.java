package com.makichanov.core.controller;

import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> read() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto read(@PathVariable Long id) {
        return orderService.find(id);
    }

    // TODO: Passing userId is stub!!!
    //  Use Spring Security ContextHolder to extract authenticated user principal instead
    @PostMapping
    public OrderDto create(@RequestBody List<Long> ticketsIds, @RequestParam Long userId) {
        return orderService.create(ticketsIds, userId);
    }

    @DeleteMapping
    public OrderDto delete(Long id) {
        return orderService.delete(id);
    }

}
