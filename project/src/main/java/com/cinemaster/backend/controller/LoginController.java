package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.WrongCredentialsException;
import com.cinemaster.backend.data.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity login(@RequestBody Credentials credentials, @CookieValue(value = "sessionid", defaultValue = "") String sessionId, HttpServletResponse httpServletResponse) {
        System.out.println(sessionId);
        if (adminService.checkAdminCredentials(credentials.getUsername(), DigestUtils.sha256Hex(credentials.getPassword())).isPresent()) {
            httpServletResponse.addCookie(new Cookie("sessionid", Integer.toString(new Random().nextInt())));
            return ResponseEntity.ok("admin\n" + sessionId);
        } else {
            throw new WrongCredentialsException();
        }
    }

}
