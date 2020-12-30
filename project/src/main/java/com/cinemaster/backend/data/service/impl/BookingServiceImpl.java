package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.controller.booking.Ticket;
import com.cinemaster.backend.core.exception.BookingAlreadyPresentException;
import com.cinemaster.backend.core.exception.InvalidDataException;
import com.cinemaster.backend.data.dao.BookingDao;
import com.cinemaster.backend.data.dao.EventDao;
import com.cinemaster.backend.data.dao.RoomDao;
import com.cinemaster.backend.data.dao.SeatDao;
import com.cinemaster.backend.data.dto.BookingDto;
import com.cinemaster.backend.data.dto.SeatDto;
import com.cinemaster.backend.data.entity.Booking;
import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.entity.Seat;
import com.cinemaster.backend.data.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        Event event = eventDao.findById(booking.getEvent().getId()).orElseThrow(() -> new InvalidDataException());
        Room room = roomDao.findById(event.getRoom().getId()).orElseThrow(() -> new InvalidDataException());
        Seat seat = seatDao.findById(booking.getSeat().getId()).orElseThrow(() -> new InvalidDataException());
        if (room.getId() != seat.getRoom().getId()) {
            throw new InvalidDataException();
        }
        List<Booking> bookings = bookingDao.findAllByEventAndSeat(booking.getEvent(), booking.getSeat());
        if (bookings.isEmpty()) {
            bookingDao.save(booking);
            bookingDto.setId(booking.getId());
        } else {
            throw new BookingAlreadyPresentException();
        }
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

    @Override
    @Transactional
    public void deleteExpired() {
        for (Booking booking : bookingDao.findAllByExpirationBefore(LocalDateTime.now())) {
            bookingDao.delete(booking);
        }
    }

    @Override
    @Transactional
    public List<SeatDto> findBookedSeatsByEventId(Long id) {
        List<SeatDto> seats = new ArrayList<>();
        for (BookingDto booking : bookingDao.findAllByEventId(id).stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList())) {
            seats.add(booking.getSeat());
        }
        return seats;
    }

    @Override
    public List<Ticket> findAllByUserId(Long id) {
        List<Ticket> tickets = new ArrayList<>();
        for (BookingDto booking : bookingDao.findAllByUserId(id).stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList())) {
            Ticket ticket = new Ticket();
            ticket.setBookingId(booking.getId());
            ticket.setUserName(booking.getUser().getFirstName() + " " + booking.getUser().getLastName());
            ticket.setShowName(booking.getEvent().getShow().getName());
            ticket.setRoomName(booking.getEvent().getRoom().getName());
            ticket.setSeat(booking.getSeat().getRow() + booking.getSeat().getColumn() + " - " + booking.getSeat().getSeatType());
            ticket.setDate(booking.getEvent().getDate());
            ticket.setStartTime(booking.getEvent().getStartTime());
            ticket.setPrice(booking.getPrice());
            tickets.add(ticket);
        }
        return tickets;
    }
}
