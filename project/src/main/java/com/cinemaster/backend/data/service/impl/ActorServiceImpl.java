package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.ActorDao;
import com.cinemaster.backend.data.dto.ActorDto;
import com.cinemaster.backend.data.entity.Actor;
import com.cinemaster.backend.data.service.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorDao actorDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(Actor actor) {
        actorDao.saveAndFlush(actor);
    }

    @Override
    public void update(Actor actor) {
        actorDao.saveAndFlush(actor);
    }

    @Override
    public void delete(Actor actor) {
        actorDao.delete(actor);
    }

    @Override
    public Optional<ActorDto> findById(Long id) {
        Optional<Actor> optional = actorDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(actor -> modelMapper.map(actor, ActorDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<ActorDto> findAll() {
        return actorDao.findAll().stream().map(actor -> modelMapper.map(actor, ActorDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ActorDto> findAllByNameContains(String name) {
        return actorDao.findAllByNameContains(name).stream().map(actor -> modelMapper.map(actor, ActorDto.class)).collect(Collectors.toList());
    }
}
