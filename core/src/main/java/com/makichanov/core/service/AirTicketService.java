package com.makichanov.core.service;


import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.model.entity.AirTicket;

import java.util.List;
//TODO: Отказываемся от интерфейсной модели сервисов
public interface AirTicketService {
    AirTicket find(Long id);

    List<AirTicket> findAll();

    AirTicket create(CreatingAirTicketDto userDto);

    AirTicket delete(Long deleteId);
}
