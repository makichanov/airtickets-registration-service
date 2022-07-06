package com.makichanov.core.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class OrderDto {

    Long id;

    Double totalPrice;

    Timestamp createDate;

    UserDto user;

    List<AirTicketDto> airTickets;

}
