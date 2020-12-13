package com.cinemaster.backend.controller;

import com.cinemaster.backend.data.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/logout")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogoutController {

    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity logout(@CookieValue(value = "sessionid", defaultValue = "") String sessionId, HttpServletResponse httpServletResponse) {
        CookieMap.getInstance().getMap().remove(sessionId);
        Cookie cookie = new Cookie("sessionid", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok("successfully logout");
    }

}
