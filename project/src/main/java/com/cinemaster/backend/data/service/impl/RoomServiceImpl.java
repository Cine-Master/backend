package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.RoomDao;
import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.entity.Show;
import com.cinemaster.backend.data.service.RoomService;
import com.cinemaster.backend.data.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ShowService showService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto, Room.class);
        roomDao.saveAndFlush(room);
        roomDto.setId(room.getId());
    }

    @Override
    public void update(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto, Room.class);
        roomDao.saveAndFlush(room);
    }

    @Override
    @Transactional
    public void delete(RoomDto roomDto) {
        Optional<Room> optional = roomDao.findById(roomDto.getId());
        if (optional.isPresent()) {
            Room room = optional.get();
            for (Show show : room.getShows()) {
                showService.delete(modelMapper.map(show, ShowDto.class));
            }
            roomDao.delete(room);
        }
    }

    @Override
    public Optional<RoomDto> findById(Long id) {
        Optional<Room> optional = roomDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(room -> modelMapper.map(room, RoomDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(room -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findAllByNameContains(String name) {
        return roomDao.findAllByNameContains(name).stream().map(room -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }
}
