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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirTicketServiceImpl implements AirTicketService {
    private final AirTicketRepository repository;
    private final ConversionService conversionService;

    @Override
    public AirTicket find(Long id) {
        return findById(id);
    }

    @Override
    public List<AirTicket> findAll() {
        return repository.findAll();
    }

    @Override
    public AirTicket create(CreatingAirTicketDto airTicketDto) {
        AirTicket airTicket = conversionService.convert(airTicketDto, AirTicket.class);
        return repository.save(airTicket);
    }

    @Override
    public AirTicket delete(Long id) {
        AirTicket airTicket = findById(id);

        repository.delete(airTicket);

        return airTicket;
    }

    private AirTicket findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AirTicket not found, requested id " + id));
    }

}
