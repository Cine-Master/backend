package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.RoomDao;
import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(Room room) {
        roomDao.saveAndFlush(room);
    }

    @Override
    public void update(Room room) {
        roomDao.saveAndFlush(room);
    }

    @Override
    public void delete(Room room) {
        roomDao.delete(room);
    }

    @Override
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(room -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }
}
