package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    void save(Director director);

    void update(Director director);

    void delete(Director director);

    Optional<DirectorDto> findById(Long id);

    List<DirectorDto> findAll();

    List<DirectorDto> findAllByNameContains(String name);
}
