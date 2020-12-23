package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.core.exception.InvalidDataException;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/rooms")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity roomList(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            return ResponseEntity.ok(roomService.findAll());
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("")
    public ResponseEntity roomAdd(@RequestBody RoomDto roomDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            try {
                roomService.save(roomDto);
                return ResponseEntity.ok(roomDto);
            } catch (RuntimeException e) {
                throw new InvalidDataException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    // TODO: business logic, how to manage (same as show)
    @PutMapping("")
    public ResponseEntity roomEdit(@RequestBody RoomDto roomDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            try {
                roomService.update(roomDto);
                return ResponseEntity.ok(roomDto);
            } catch (RuntimeException e) {
                throw new InvalidDataException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("")
    public ResponseEntity roomDelete(@RequestBody RoomDto roomDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            try {
                roomService.delete(roomDto);
                return ResponseEntity.ok("Successfully deleted");
            } catch (RuntimeException e) {
                throw new InvalidDataException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

}
