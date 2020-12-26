package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.BookingDao;
import com.cinemaster.backend.data.dto.BookingDto;
import com.cinemaster.backend.data.entity.Booking;
import com.cinemaster.backend.data.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        bookingDao.save(booking);
        bookingDto.setId(booking.getId());
    }

    // TODO come la delete?
    @Override
    public void update(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        bookingDao.save(booking);
    }

    @Override
    public void delete(BookingDto bookingDto) {
// TODO mo vediamo
    }

    @Override
    public Optional<BookingDto> findById(Long id) {
        Optional<Booking> optional = bookingDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(booking -> modelMapper.map(booking, BookingDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<BookingDto> findAll() {
        return bookingDao.findAll().stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
    }
}
