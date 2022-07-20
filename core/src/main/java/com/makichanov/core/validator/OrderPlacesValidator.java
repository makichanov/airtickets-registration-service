package com.makichanov.core.validator;

import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.CreateOrderRequestDto;
import com.makichanov.core.model.request.RouteDto;
import com.makichanov.core.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class OrderPlacesValidator implements Validator {
    private final FlightService flightService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateOrderRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateOrderRequestDto dto = (CreateOrderRequestDto) target;

        for (RouteDto r : dto.getRoutes()) {
            FlightDetails flightDetails = flightService.findByRoute(r.getFlightFromId(), r.getFlightToId());
            long placesToBeSold = flightDetails.getPlacesSold() + r.getTicketsCount();

            if (placesToBeSold > flightDetails.getMaxPlaces()) {
                errors.rejectValue("maxPlaces" + flightDetails.getId(), "excess");
            }
        }
    }
}
