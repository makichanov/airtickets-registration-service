package com.makichanov.core.model.request;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class UpdateAirTicketRequestDto {
    @Positive(message = "Place cannot be negative")
    private Integer place;
}
