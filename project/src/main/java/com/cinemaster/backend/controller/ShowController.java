package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/admin/shows")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping("")
//    public ResponseEntity showList() {
//    }

    @PostMapping("/add")
    public ResponseEntity showAdd(@RequestBody ShowDto showDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId, HttpServletResponse httpServletResponse) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            showService.save(showDto);
            return ResponseEntity.ok(showDto);
        } else {
            throw new ForbiddenException();
        }
    }

//    @GetMapping("/edit")
//    public ResponseEntity showEdit() {
//    }

//    @GetMapping("/remove")
//    public ResponseEntity showRemove() {
//    }

}
