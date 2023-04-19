package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateOrderRequest;
import com.makichanov.core.model.response.OrderDto;
import com.makichanov.core.entity.Order;
import com.makichanov.core.service.OrderService;
import com.makichanov.core.util.converter.ConversionUtils;
import com.makichanov.core.validator.OrderPlacesValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Order controller", description = "CRD operations for Order entity")
public class OrderController {
    private final OrderService orderService;
    private final ConversionService conversionService;
    private final OrderPlacesValidator orderPlacesValidator;

    @InitBinder("createOrderRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(orderPlacesValidator);
    }

    @GetMapping
    @Operation(summary = "Read all orders by optional page parameters and user id",
            description = "Returns list of found orders")
    public ResponseEntity<List<OrderDto>> readAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<Order> orders = userId == null
                ? orderService.findAll(pageNum, pageSize)
                : orderService.findByUserId(userId);

        return ResponseEntity.ok(ConversionUtils.toOrdersDtoList(orders));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read order by its id", description = "Returns found order")
    public ResponseEntity<OrderDto> read(@PathVariable Long id) {
        Order order = orderService.find(id);

        return ResponseEntity.ok(conversionService.convert(order, OrderDto.class));
    }

    @PostMapping
    @Operation(summary = "Create order", description = "Creates order, returns created Order entity")
    public ResponseEntity<OrderDto> create(@RequestBody @Valid CreateOrderRequest orderRequest) {
        Order order = orderService.create(orderRequest.getRoutes());

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order", description = "deletes order by id, returns deleted entity")
    public ResponseEntity<OrderDto> delete(@PathVariable Long id) {
        Order order = orderService.delete(id);

        return ResponseEntity.ok(conversionService.convert(order, OrderDto.class));
    }
}
