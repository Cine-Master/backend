package com.cinemaster.backend.controller.booking;

import com.cinemaster.backend.controller.CookieMap;
import com.cinemaster.backend.core.exception.BookingAlreadyPresentException;
import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.core.exception.InvalidDataException;
import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private ModelMapper modelMapper;

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
                    bookingDto.setUser(modelMapper.map(accountDto, UserDto.class));
                    bookingDto.setPayed(false);
                    bookingDto.setExpiration(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));
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


//    @GetMapping("/search")
//    public ResponseEntity showListByName(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "coming-soon", required = false) Boolean comingSoon,
//            @RequestParam(value = "highlighted", required = false) Boolean highlighted,
//            @RequestParam(value = "category", required = false) String[] categories) {
//        return ResponseEntity.ok(showService.findAllByFilter(new ShowSpecification.Filter(name, comingSoon, highlighted, categories)));
//    }


}
