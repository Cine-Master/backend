package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/rooms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity showList(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            return ResponseEntity.ok(roomService.findAll());
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/add")
    public ResponseEntity showAdd(@RequestBody RoomDto roomDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            roomService.save(roomDto);
            return ResponseEntity.ok(roomDto);
        } else {
            throw new ForbiddenException();
        }
    }


    // TODO: business logic, how to manage
//    @GetMapping("/edit")
//    public ResponseEntity showEdit() {
//    }

//    @GetMapping("/remove")
//    public ResponseEntity showRemove() {
//    }

}
