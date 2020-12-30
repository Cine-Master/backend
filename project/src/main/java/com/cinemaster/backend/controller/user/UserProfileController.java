package com.cinemaster.backend.controller.user;

import com.cinemaster.backend.controller.login.CookieMap;
import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.UserPasswordLessDto;
import com.cinemaster.backend.data.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class UserProfileController {

    @Autowired
    private BookingService bookingService;

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

}
