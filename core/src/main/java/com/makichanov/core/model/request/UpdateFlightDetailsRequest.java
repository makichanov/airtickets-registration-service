package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Schema(description = "Update flight details request data")
public class UpdateFlightDetailsRequest {

    @Future(message = "It doesn't make sense to create flight for past time")
    @Schema(description = "New date of flight departure")
    private LocalDateTime departureTime;

    @Future(message = "It doesn't make sense to create flight for past time")
    @Schema(description = "New date of flight arrival")
    private LocalDateTime arrivalTime;

    @Positive(message = "Prise value should be positive")
    @Schema(description = "New flight base price in dollars")
    private Double basePrice;

    @Positive(message = "Max places number should be positive")
    @Schema(description = "new maximal number of places in plate")
    private Integer maxPlaces;

    @Positive(message = "Sold places number should be positive")
    @Schema(description = "New number of sold places")
    private Integer placesSold;

    @Positive(message = "Id value is always positive")
    @Schema(description = "New id of departure flight address")
    private Long flightFromId;

    @Positive(message = "Id value is always positive")
    @Schema(description = "New id of arrival flight address")
    private Long flightToId;
}
