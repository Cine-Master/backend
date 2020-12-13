package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.ActorDto;
import com.cinemaster.backend.data.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    void save(Actor actor);

    void update(Actor actor);

    void delete(Actor actor);

    Optional<ActorDto> findById(Long id);

    List<ActorDto> findAll();

    List<ActorDto> findAllByNameContains(String name);
}
