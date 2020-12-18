package com.cinemaster.backend.controller;

import com.cinemaster.backend.data.service.ShowService;
import com.cinemaster.backend.data.specification.ShowSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shows")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        return ResponseEntity.ok(showService.findById(id));
    }

}
