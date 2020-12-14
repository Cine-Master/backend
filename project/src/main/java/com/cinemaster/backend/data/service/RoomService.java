package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void save(RoomDto roomDto);

    void update(RoomDto roomDto);

    void delete(RoomDto roomDto);

    Optional<RoomDto> findById(Long id);

    List<RoomDto> findAll();

    List<RoomDto> findAllByNameContains(String name);
}
