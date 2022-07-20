package com.makichanov.core.service;


import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.request.CreateAirTicketRequestDto;
import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.model.request.UpdateAirTicketRequestDto;
import com.makichanov.core.repository.AirTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirTicketService {
    private final AirTicketRepository repository;

    public AirTicket find(Long id) {
        return findById(id);
    }

    public List<AirTicket> findAll(Long pageNum, Long pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum.intValue(), pageSize.intValue());
        return repository.findAll(pageRequest)
                .getContent();
    }

    public AirTicket create(AirTicket airTicket) {
        return repository.save(airTicket);
    }

    @Transactional
    public AirTicket update(Long id, AirTicket updated) {
        AirTicket airTicket = findById(id);
        airTicket.setPlace(updated.getPlace());
        return airTicket;
    }

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
