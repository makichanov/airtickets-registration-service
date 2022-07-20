package com.makichanov.core.model.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class CreateFlightDetailsRequestDto {

    @Future(message = "It doesn't make sense to create flight for past time")
    private LocalDateTime departureTime;

    @Future(message = "It doesn't make sense to create flight for past time")
    private LocalDateTime arrivalTime;

    @Positive(message = "Prise value should be positive")
    private Double basePrice;

    @Positive(message = "Id value is always positive")
    private Long flightFromId;

    @Positive(message = "Id value is always positive")
    private Long flightToId;

    @Positive(message = "Max places number should be positive")
    private Integer maxPlaces;

}
