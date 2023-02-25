package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public List<EventDTO> findAll() {

        List<Event> list = repository.findAll();

        return list.stream().map(event -> new EventDTO(event)).collect(Collectors.toList());
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {

        try {
            Event entity = repository.getOne(id);

            entity.setName(dto.getName());
            entity.setDate(dto.getDate());
            entity.setUrl(dto.getUrl());
            entity.setCity(new City(dto.getCityId(), null));
            entity = repository.save(entity);

            return new EventDTO(entity);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }

    }
}