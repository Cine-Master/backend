package com.cinemaster.backend.controller;

import com.cinemaster.backend.core.exception.WrongCredentialsException;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity login(@RequestBody Credentials credentials, @CookieValue(value = "sessionid", defaultValue = "") String sessionId, HttpServletResponse httpServletResponse) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null) {
            if (accountDto instanceof AdminPasswordLessDto) {
                AdminPasswordLessDto adminDto = (AdminPasswordLessDto) accountDto;
                return ResponseEntity.ok(adminDto);
            }
        }
        Optional<AccountPasswordLessDto> optional = accountService.checkCredentials(credentials.getUsername(), DigestUtils.sha256Hex(credentials.getPassword()));
        if (optional.isPresent()) {
            String cookieString = DigestUtils.sha256Hex(Integer.toString(new Random().nextInt()));
            Cookie cookie = new Cookie("sessionid", cookieString);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            AccountPasswordLessDto dto = optional.get();
            if (dto instanceof AdminPasswordLessDto) {
                AdminPasswordLessDto adminDto = (AdminPasswordLessDto) dto;
                CookieMap.getInstance().getMap().put(cookieString, adminDto);
                return ResponseEntity.ok(adminDto);
            }
            // else if user ecc
        } else {
            throw new WrongCredentialsException();
        }
        return null;
    }

}
