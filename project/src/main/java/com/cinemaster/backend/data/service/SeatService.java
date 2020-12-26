package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.SeatDto;

import java.util.List;
import java.util.Optional;

public interface SeatService {

    void save(SeatDto seatDto);

    void update(SeatDto seatDto);

    void delete(SeatDto seatDto);

    Optional<SeatDto> findById(Long id);

    List<SeatDto> findAll();
}
