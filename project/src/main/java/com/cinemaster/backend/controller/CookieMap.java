package com.cinemaster.backend.controller;

import java.util.HashMap;

public class CookieMap {

    private static CookieMap instance;
    private HashMap<String, String> map;

    private CookieMap() {
        map = new HashMap<>();
    }

    public static CookieMap getInstance() {
        if (instance == null) {
            instance = new CookieMap();
        }
        return instance;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
