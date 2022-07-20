package com.makichanov.core.controller;

import com.makichanov.core.model.request.CreateOrderRequestDto;
import com.makichanov.core.model.response.OrderDto;
import com.makichanov.core.entity.Order;
import com.makichanov.core.service.OrderService;
import com.makichanov.core.util.converter.ConversionUtils;
import com.makichanov.core.validator.OrderPlacesValidator;
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
public class OrderController {
    private final OrderService orderService;
    private final ConversionService conversionService;
    private final OrderPlacesValidator orderPlacesValidator;

    @InitBinder("createOrderRequestDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(orderPlacesValidator);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> readAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<Order> orders = userId == null
                ? orderService.findAll(pageNum, pageSize)
                : orderService.findByUserId(userId);

        return new ResponseEntity<>(ConversionUtils.toOrdersDtoList(orders), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> read(@PathVariable Long id) {
        Order order = orderService.find(id);

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid CreateOrderRequestDto orderRequest) {
        Order order = orderService.create(orderRequest.getRoutes());

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@PathVariable Long id) {
        Order order = orderService.delete(id);

        return new ResponseEntity<>(conversionService.convert(order, OrderDto.class), HttpStatus.OK);
    }
}
