package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;

import java.util.List;

public interface DirectorService {

    void save(Director director);

    void update(Director director);

    void delete(Director director);

    List<DirectorDto> findAll();
}
