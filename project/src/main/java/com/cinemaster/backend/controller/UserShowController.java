package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.ShowNotFoundException;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.service.ShowService;
import com.cinemaster.backend.data.specification.ShowSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/shows")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class UserShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("")
    public ResponseEntity showList() {
        return ResponseEntity.ok(showService.findAll());
    }


    @GetMapping("/search")
    public ResponseEntity showListByName(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String[] categories) {
        return ResponseEntity.ok(showService.findAllByFilter(new ShowSpecification.Filter(name, categories)));
    }

    @GetMapping("/details")
    public ResponseEntity showDetails(
            @RequestParam(value = "id", required = true) Long id) {
        Optional<ShowDto> optional = showService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(showService.findById(id));
        } else {
            throw new ShowNotFoundException();
        }
    }

}
