package com.makichanov.core.controller;

import com.makichanov.core.model.dto.OrderDto;
import com.makichanov.core.model.entity.FlightAddress;
import com.makichanov.core.model.entity.Order;
import com.makichanov.core.service.OrderService;
import com.makichanov.core.util.converter.ConversionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ConversionService conversionService;
    private final ConversionUtils conversionUtils;

    @GetMapping
    public ResponseEntity<List<OrderDto>> read() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(conversionUtils.toOrdersDtoList(orders), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> read(@PathVariable Long id) {
        Order order = orderService.find(id);
        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody FlightAddress from, FlightAddress to) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        Order order = orderService.create(from, to , username);
        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.CREATED);
    }
    //TODO: Было бы хорошим тоном отличать dto, которые идут в запрос от dto, которые приходят в ответе. Рефактор на EntityNameRequest и EntityNameResponse
    @DeleteMapping
    public ResponseEntity<OrderDto> delete(Long id) {
        Order order = orderService.delete(id);
        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }
}
