package com.makichanov.core.service;


import com.makichanov.core.model.request.CreateAirTicketRequestDto;
import com.makichanov.core.entity.AirTicket;

import java.util.List;
//TODO: Отказываемся от интерфейсной модели сервисов
public interface AirTicketService {
    AirTicket find(Long id);

    List<AirTicket> findAll();

    AirTicket create(CreateAirTicketRequestDto userDto);

    AirTicket delete(Long deleteId);
}
