package com.makichanov.core.validator;

import com.makichanov.core.entity.FlightDetails;
import com.makichanov.core.model.request.CreateOrderRequest;
import com.makichanov.core.model.request.Route;
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
        return CreateOrderRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateOrderRequest dto = (CreateOrderRequest) target;

        for (Route r : dto.getRoutes()) {
            FlightDetails flightDetails = flightService.findByRoute(r.getFlightFromId(), r.getFlightToId());

            if (flightDetails.getPlaces() < r.getTicketsCount()) {
                errors.rejectValue("routes", "excess");
            }
        }
    }
}
