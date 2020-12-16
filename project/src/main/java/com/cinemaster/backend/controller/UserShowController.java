package com.cinemaster.backend.controller;

import com.cinemaster.backend.data.service.ShowService;
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


    //TODO vediamo un po' (perché non passare direttamente gli id delle categorie dato che le facciamo via checkbox)
//    @GetMapping("/search")
//    public ResponseEntity showListByName(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "category", required = false) String category) {
//        if (name != null) {
//            return ResponseEntity.ok(showService.findAllByNameContains(name));
//        } else if (category != null) {
//            return ResponseEntity.ok(showService.findAllByCategoriesNames(category));
//        }
//    }

//    @GetMapping("")
//    public ResponseEntity showDetails(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
//        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
//        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
//            return ResponseEntity.ok(showService.findAll());
//        } else {
//            throw new ForbiddenException();
//        }
//    }

}
