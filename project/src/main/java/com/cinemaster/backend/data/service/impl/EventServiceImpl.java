package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.core.exception.ShowNotFoundException;
import com.cinemaster.backend.data.dao.EventDao;
import com.cinemaster.backend.data.dto.EventDto;
import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.service.EventService;
import com.cinemaster.backend.data.specification.EventSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Optional<EventDto> save(EventDto eventDto) {
        List<Event> events = eventDao.findAll(EventSpecification.findAllBy(modelMapper.map(eventDto.getRoom(), Room.class), eventDto.getDate(), eventDto.getStartTime(), eventDto.getEndTime()));
        if (events.isEmpty()) {
            Event event = modelMapper.map(eventDto, Event.class);
            eventDao.saveAndFlush(event);
            eventDto.setId(event.getId());
            return Optional.empty();
        } else {
            return Optional.of(eventDto);
        }
    }

    // TODO come nella delete?
    @Override
    public void update(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        eventDao.saveAndFlush(event);
    }

    @Override
    public void delete(EventDto eventDto) {
        // TODO mo vediamo
    }

    @Override
    public Optional<EventDto> findById(Long id) {
        Optional<Event> optional = eventDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(event -> modelMapper.map(event, EventDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<EventDto> findAll() {
        return eventDao.findAll().stream().map(event -> modelMapper.map(event, EventDto.class)).collect(Collectors.toList());
    }
}
