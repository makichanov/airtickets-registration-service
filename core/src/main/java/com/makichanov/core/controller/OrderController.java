package com.makichanov.core.controller;

import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping
    public OrderDto create(@RequestBody FlightAddress from, FlightAddress to) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        return orderService.create(from, to , username);
    }
    //TODO: Было бы хорошим тоном отличать dto, которые идут в запрос от dto, которые приходят в ответе. Рефактор на EntityNameRequest и EntityNameResponse
    @DeleteMapping
    public OrderDto delete(Long id) {
        return orderService.delete(id);
    }
}
