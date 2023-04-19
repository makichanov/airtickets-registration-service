package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Schema(description = "Create flight details request data")
public class CreateFlightDetailsRequest {

    @Future(message = "It doesn't make sense to create flight for past time")
    @Schema(description = "Date of flight departure")
    private LocalDateTime departureTime;

    @Future(message = "It doesn't make sense to create flight for past time")
    @Schema(description = "Date of flight arrival")
    private LocalDateTime arrivalTime;

    @Positive(message = "Prise value should be positive")
    @Schema(description = "Flight base price in dollars")
    private Double basePrice;

    @Positive(message = "Id value is always positive")
    @Schema(description = "Id of departure flight address")
    private Long flightFromId;

    @Positive(message = "Id value is always positive")
    @Schema(description = "Id of arrival flight address")
    private Long flightToId;

    @Positive(message = "Max places number should be positive")
    @Schema(description = "Maximal number of places in plate, determines maximal amount of tickets might be sold")
    private Integer places;

}
