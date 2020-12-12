package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.entity.Show;

import java.util.List;

public interface ShowService {

    void save(Show show);

    void update(Show show);

    void delete(Show show);

    List<ShowDto> findAll();

    List<ShowDto> findAllByName(String name);

    // TODO check
    List<ShowDto> findAllByCategories(String... categories);
}
