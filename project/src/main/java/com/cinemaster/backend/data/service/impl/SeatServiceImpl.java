package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.SeatDao;
import com.cinemaster.backend.data.dto.SeatDto;
import com.cinemaster.backend.data.entity.Seat;
import com.cinemaster.backend.data.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(SeatDto seatDto) {
        Seat seat = modelMapper.map(seatDto, Seat.class);
        seatDao.save(seat);
        seatDto.setId(seat.getId());
    }

    // TODO Come la delete?
    @Override
    public void update(SeatDto seatDto) {
        Seat seat = modelMapper.map(seatDto, Seat.class);
        seatDao.save(seat);
    }

    @Override
    public void delete(SeatDto seatDto) {
// TODO mo vediamo
    }

    @Override
    public Optional<SeatDto> findById(Long id) {
        Optional<Seat> optional = seatDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(seat -> modelMapper.map(seat, SeatDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<SeatDto> findAll() {
        return seatDao.findAll().stream().map(seat -> modelMapper.map(seat, SeatDto.class)).collect(Collectors.toList());
    }
}
