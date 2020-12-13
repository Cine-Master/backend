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
        String username = CookieMap.getInstance().getMap().get(sessionId);
        if (username != null) {
            return ResponseEntity.ok(username);
        }
        if (adminService.checkAdminCredentials(credentials.getUsername(), DigestUtils.sha256Hex(credentials.getPassword())).isPresent()) {
            String cookieString = DigestUtils.sha256Hex(Integer.toString(new Random().nextInt()));
            CookieMap.getInstance().getMap().put(cookieString, credentials.getUsername());
            Cookie cookie = new Cookie("sessionid", cookieString);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.ok("admin\n" + cookie);
        } else {
            throw new WrongCredentialsException();
        }
    }

}
