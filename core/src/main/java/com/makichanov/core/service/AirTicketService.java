package com.makichanov.core.service;


import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.CreatingAirTicketDto;

import java.util.List;

public interface AirTicketService {

    AirTicketDto find(Long id);

    List<AirTicketDto> findAll();

    AirTicketDto create(CreatingAirTicketDto userDto);

    AirTicketDto delete(Long deleteId);

}
