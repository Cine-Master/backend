package com.cinemaster.backend.controller.booking;

import com.cinemaster.backend.controller.login.CookieMap;
import com.cinemaster.backend.core.exception.*;
import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.service.*;
import com.cinemaster.backend.email.EmailService;
import com.cinemaster.backend.pdf.PdfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private CouponService couponService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/coupon")
    public ResponseEntity validateCoupon(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody String code) {
        System.out.println(code);
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            Optional<CouponDto> optional = couponService.findByCode(code);
            if (!(optional.isPresent())) {
                throw new CouponNotFoundException();
            }
            CouponDto coupon = optional.get();
            if (!(couponService.findAllByUserId(accountDto.getId()).contains(coupon))) {
                throw new CouponNotFoundException();
            } else {
                coupon.setUsed(true);
                couponService.update(coupon);
                return ResponseEntity.ok(optional.get().getValue());
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/select")
    public ResponseEntity selectBooking(
            @CookieValue(value = "sessionid", defaultValue = "") String sessionId,
            @RequestBody List<BookingCreationParams> bookingCreationParamsList) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof UserPasswordLessDto) {
            List<BookingDto> bookings = new ArrayList<>();
            try {
                for (BookingCreationParams bookingCreationParams : bookingCreationParamsList) {
                    for (SeatDto seat : bookingCreationParams.getSeats()) {
                        BookingDto bookingDto = new BookingDto();
                        bookingDto.setEvent(bookingCreationParams.getEvent());
                        bookingDto.setSeat(seat);
                        bookingDto.setUser(modelMapper.map(accountDto, UserPasswordLessDto.class));
                        bookingDto.setPayed(false);
                        bookingDto.setExpiration(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));
                        bookingDto.setPrice(0D);
                        bookingService.save(bookingDto);
                        bookings.add(bookingDto);
                    }
                }
            } catch (EventNotFoundException | RoomNotFoundException | SeatNotFoundException | InvalidDataException | BookingAlreadyPresentException e) {
                for (BookingDto bookingDto : bookings) {
                    bookingService.delete(bookingDto);
                }
                throw e;
            }
            return ResponseEntity.ok(bookings);
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
                BookingDto booking = bookingService.findById(param.getBooking().getId()).orElseThrow(() -> new BookingNotFoundException());
                booking.setPayed(true);
                booking.setPrice(param.getPrice());
                booking.setExpiration(null);
                bookingService.update(booking);

                EventDto event = eventService.findById(booking.getEvent().getId()).orElseThrow(() -> new EventNotFoundException());
                SeatDto seat = seatService.findById(booking.getSeat().getId()).orElseThrow(() -> new SeatNotFoundException());

                TicketDto ticketDto = new TicketDto();
                ticketDto.setBookingId(booking.getId());
                ticketDto.setUserName(((UserPasswordLessDto) accountDto).getFirstName() + " " + ((UserPasswordLessDto) accountDto).getLastName());
                ticketDto.setShowName(event.getShow().getName());
                ticketDto.setRoomName(event.getRoom().getName());
                ticketDto.setSeat(seat.getRow() + seat.getColumn() + " - " + seat.getSeatType());
                ticketDto.setDate(event.getDate());
                ticketDto.setStartTime(event.getStartTime());
                ticketDto.setPrice(booking.getPrice());
                String barcode;
                do {
                    barcode = ticketDto.generateBarcodeEAN13();
                } while (ticketService.findByBarcode(barcode).isPresent());
                ticketDto.setBarcode(barcode);
                ticketService.save(ticketDto);

                String ticketPath = pdfService.createPdf(ticketDto);
                // TODO inviare un messaggio più bello, con i dettagli dell'ordine
                emailService.sendTicketEmail(((UserPasswordLessDto) accountDto).getEmail(), ticketDto, ticketPath);
            }
            couponService.deleteAllByUserIdAndIsUsed(accountDto.getId());
            return ResponseEntity.ok("Booking confirmed successfully");
        } else {
            throw new ForbiddenException();
        }
    }

}
