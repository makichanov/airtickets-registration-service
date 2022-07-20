package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateOrderRequestDto;
import com.makichanov.core.model.response.OrderDto;
import com.makichanov.core.entity.Order;
import com.makichanov.core.service.OrderService;
import com.makichanov.core.util.converter.ConversionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> readAll(@RequestParam(required = false) Long userId) {
        List<Order> orders = userId == null
                ? orderService.findAll()
                : orderService.findByUserId(userId);

        return new ResponseEntity<>(ConversionUtils.toOrdersDtoList(orders), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> read(@PathVariable Long id) {
        Order order = orderService.find(id);

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderRequestDto dto) {
        Order order = orderService.create(dto.getRoutes());

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@PathVariable Long id) {
        Order order = orderService.delete(id);

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }
}
