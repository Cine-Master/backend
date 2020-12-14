package com.cinemaster.backend.controller;

import com.cinemaster.backend.data.dto.AccountDto;

import java.util.HashMap;

public class CookieMap {

    private static CookieMap instance;
    private HashMap<String, AccountDto> map;

    private CookieMap() {
        map = new HashMap<>();
    }

    public static CookieMap getInstance() {
        if (instance == null) {
            instance = new CookieMap();
        }
        return instance;
    }

    public HashMap<String, AccountDto> getMap() {
        return map;
    }
}
