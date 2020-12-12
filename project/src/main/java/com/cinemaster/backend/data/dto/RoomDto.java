package com.cinemaster.backend.data.dto;

import com.cinemaster.backend.data.entity.Show;

import java.util.List;

public class RoomDto {

    private Long id;

    private String name;

    private List<Show> shows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

}
