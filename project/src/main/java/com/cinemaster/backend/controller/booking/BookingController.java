package com.cinemaster.backend.controller.booking;

import com.cinemaster.backend.controller.login.CookieMap;
import com.cinemaster.backend.core.exception.BookingAlreadyPresentException;
import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.core.exception.InvalidDataException;
import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.service.BookingService;
import com.cinemaster.backend.data.service.EventService;
import com.cinemaster.backend.data.service.SeatService;
import com.cinemaster.backend.email.EmailService;
import com.cinemaster.backend.pdf.PdfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/booking")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class BookingController {

    public static final Long EXPIRATION_MINUTES = 10L;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/select")
    public ResponseEntity selectBooking(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody BookingCreationParams bookingCreationParams) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            List<BookingDto> bookings = new ArrayList<>();
            try {
                for (SeatDto seatDto : bookingCreationParams.getSeats()) {
                    BookingDto bookingDto = new BookingDto();
                    bookingDto.setEvent(bookingCreationParams.getEvent());
                    bookingDto.setSeat(seatDto);
                    bookingDto.setUser(modelMapper.map(accountDto, UserPasswordLessDto.class));
                    bookingDto.setPayed(false);
                    bookingDto.setExpiration(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));
                    bookingDto.setPrice(0D);
                    bookingService.save(bookingDto);
                    bookings.add(bookingDto);
                }
            } catch (InvalidDataException | BookingAlreadyPresentException e) {
                for (BookingDto bookingDto : bookings) {
                    bookingService.delete(bookingDto);
                }
                throw e;
            }
            return ResponseEntity.ok("Booked successfully");
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity confirmBooking(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody BookingConfirmationParams[] bookings) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            for (BookingConfirmationParams param : bookings) {
                BookingDto booking = bookingService.findById(param.getBooking().getId()).orElseThrow(() -> new InvalidDataException());
                booking.setPayed(true);
                booking.setPrice(param.getPrice());
                booking.setExpiration(null);
                bookingService.update(booking);

                EventDto event = eventService.findById(booking.getEvent().getId()).orElseThrow(() -> new InvalidDataException());
                SeatDto seat = seatService.findById(booking.getSeat().getId()).orElseThrow(() -> new InvalidDataException());

                Ticket ticket = new Ticket();
                ticket.setBookingId(booking.getId());
                ticket.setUserName(((UserPasswordLessDto) accountDto).getFirstName() + " " + ((UserPasswordLessDto) accountDto).getLastName());
                ticket.setShowName(event.getShow().getName());
                ticket.setRoomName(event.getRoom().getName());
                ticket.setSeat(seat.getRow() + seat.getColumn() + " - " + seat.getSeatType());
                ticket.setDate(event.getDate());
                ticket.setStartTime(event.getStartTime());
                ticket.setPrice(booking.getPrice());

                String ticketPath = pdfService.createPdf(ticket);
                // TODO inviare un messaggio più bello, con i dettagli dell'ordine
                emailService.sendEmail(((UserPasswordLessDto) accountDto).getEmail(), String.format("Prenotazione #%d confermata", booking.getId()), "In allegato può trovare il biglietto. Si prega di stamparlo ed esporlo all'ingresso prima dell'evento.\nLa ringraziamo per averci scelto!", ticketPath);
            }
            return ResponseEntity.ok("Booking confirmed successfully");
        } else {
            throw new ForbiddenException();
        }
    }

}
