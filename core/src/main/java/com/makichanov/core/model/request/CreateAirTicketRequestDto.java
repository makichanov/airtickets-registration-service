package com.makichanov.core.model.request;

import com.makichanov.core.model.response.FlightAddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Schema(description = "Dto for creating airticket entity")
public class CreateAirTicketRequestDto {

    @Schema(description = "Ticket price in dollars")
    @Positive(message = "Price cannot be negative")
    private Double price;

    @Schema(description = "Ticket place in plane")
    @Positive(message = "Place cannot be negative")
    private Integer place;

    @Schema(description = "Flight departure time")
    @Future(message = "Ticket cannot be ordered for past time")
    private LocalDateTime departureTime;

    @Schema(description = "Flight arrival time")
    @Future(message = "Ticket cannot be ordered for past time")
    private LocalDateTime arrivalTime;

    @Schema(description = "Departure address")
    private FlightAddressDto addressFrom;

    @Schema(description = "Destination address")
    private FlightAddressDto addressTo;

}
