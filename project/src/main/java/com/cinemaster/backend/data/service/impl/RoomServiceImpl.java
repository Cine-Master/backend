package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.RoomDao;
import com.cinemaster.backend.data.dao.SeatDao;
import com.cinemaster.backend.data.dto.EventDto;
import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.dto.SeatDto;
import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.entity.Seat;
import com.cinemaster.backend.data.service.EventService;
import com.cinemaster.backend.data.service.RoomService;
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
    private EventService eventService;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto, Room.class);
        roomDao.saveAndFlush(room);
        roomDto.setId(room.getId());

        for (SeatDto seatDto : roomDto.getSeats()) {
            Seat seat = modelMapper.map(seatDto, Seat.class);
            seat.setRoom(room);
            seatDao.save(seat);
        }
    }

    // TODO qualcosa tipo la delete
    @Override
    @Transactional
    public void update(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto, Room.class);
        roomDao.saveAndFlush(room);

        for (SeatDto seatDto : roomDto.getSeats()) {
            Seat seat = modelMapper.map(seatDto, Seat.class);
            seat.setRoom(room);
            seatDao.save(seat);
        }
    }

    @Override
    @Transactional
    public void delete(RoomDto roomDto) {
        Optional<Room> optional = roomDao.findById(roomDto.getId());
        if (optional.isPresent()) {
            Room room = optional.get();
            for (Event event : room.getEvents()) {
                eventService.delete(modelMapper.map(event, EventDto.class));
            }
            for (Seat seat : room.getSeats()) {
                seatDao.delete(seat);
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
