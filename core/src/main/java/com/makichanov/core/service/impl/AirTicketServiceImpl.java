package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.AirTicketDto;
import com.makichanov.core.model.dto.CreatingAirTicketDto;
import com.makichanov.core.model.entity.AirTicket;
import com.makichanov.core.repository.AirTicketRepository;
import com.makichanov.core.service.AirTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO: Почему маппинг в дто выполняется на уровне сервиса? Вынести в контроллер. Возвращаемое значение методов сервисного класса  --  не DTO.
// возвращать сущности, либо списки сущностей
@Service
@RequiredArgsConstructor
public class AirTicketServiceImpl implements AirTicketService {

    private final AirTicketRepository repository;
    private final ConversionService conversionService;

    //TODO: Для всех методов ниже: разделяем пустыми строками логические блоки.
    @Override
    public AirTicketDto find(Long id) {
        Optional<AirTicket> airTicket = repository.findById(id);
        AirTicket item = airTicket.orElseThrow(
                () -> new EntityNotFoundException("AirTicket not found, requested id " + id));
        return conversionService.convert(item, AirTicketDto.class);
    }

    @Override
    public List<AirTicketDto> findAll() {
        List<AirTicket> airTickets = repository.findAll();
        return airTickets.stream()
                .map(t -> conversionService.convert(t, AirTicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AirTicketDto create(CreatingAirTicketDto airTicketDto) {
        AirTicket airTicket = conversionService.convert(airTicketDto, AirTicket.class);
        AirTicket persisted = repository.save(airTicket);
        return conversionService.convert(persisted, AirTicketDto.class);
    }

    //TODO: 1) избыточность в названии параметра метода, достаточно просто id
    //      2) приводить к единообразию: если в find параметр id, то и в delete пусть будет тоже id
    @Override
    public AirTicketDto delete(Long deleteId) {
        Optional<AirTicket> airTicket = repository.findById(deleteId);
        AirTicket item = airTicket.orElseThrow(
                () -> new EntityNotFoundException("AirTicket not found, requested id " + deleteId));
        repository.delete(item);
        return conversionService.convert(item, AirTicketDto.class);
    }

}
