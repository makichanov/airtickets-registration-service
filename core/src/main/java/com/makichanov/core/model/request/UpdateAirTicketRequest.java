package com.makichanov.core.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@Schema(description = "Update air ticket request data")
public class UpdateAirTicketRequest {

    @Positive(message = "Place cannot be negative")
    @Schema(description = "New place of air ticket")
    private Integer place;
}
