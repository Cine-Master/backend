package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.entity.Room;

import java.util.List;

public interface RoomService {

    void save(Room room);

    void update(Room room);

    void delete(Room room);

    List<RoomDto> findAll();

}
