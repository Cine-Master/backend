package com.cinemaster.backend.controller.user;

import com.cinemaster.backend.controller.booking.Ticket;
import com.cinemaster.backend.controller.login.CookieMap;
import com.cinemaster.backend.core.exception.*;
import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.service.AccountService;
import com.cinemaster.backend.data.service.BookingService;
import com.cinemaster.backend.data.service.EventService;
import com.cinemaster.backend.data.service.SeatService;
import com.cinemaster.backend.email.EmailService;
import com.cinemaster.backend.pdf.PdfService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class UserProfileController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EventService eventService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/profile")
    public ResponseEntity profile(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            return ResponseEntity.ok(accountDto);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity bookings(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            return ResponseEntity.ok(bookingService.findAllByUserId(accountDto.getId()));
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody UserDto userDto) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            userDto.setHashedPassword(DigestUtils.sha256Hex(userDto.getHashedPassword()));
            accountService.update(userDto);
            UserPasswordLessDto userPasswordLessDto = modelMapper.map(userDto, UserPasswordLessDto.class);
            CookieMap.getInstance().getMap().remove(sessionId);
            CookieMap.getInstance().getMap().put(sessionId, userPasswordLessDto);
            return ResponseEntity.ok(userPasswordLessDto);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/tickets/re-send")
    public ResponseEntity reSendTicket(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody BookingDto dto) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            BookingDto booking = bookingService.findById(dto.getId()).orElseThrow(() -> new BookingNotFoundException());
            if (!(booking.getPayed())) {
                throw new BookingNotPayedException();
            }

            EventDto event = eventService.findById(booking.getEvent().getId()).orElseThrow(() -> new EventNotFoundException());
            SeatDto seat = seatService.findById(booking.getSeat().getId()).orElseThrow(() -> new SeatNotFoundException());

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
            emailService.sendTicketEmail(((UserPasswordLessDto) accountDto).getEmail(), ticket, ticketPath);
            return ResponseEntity.ok("Ticket sent by email");
        } else {
            throw new ForbiddenException();
        }
    }

}
