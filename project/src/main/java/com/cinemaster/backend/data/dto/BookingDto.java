package com.cinemaster.backend.data.dto;

import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Seat;
import com.cinemaster.backend.data.entity.User;

public class BookingDto {

    private Long id;

    private Event event;

    private Seat seat;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
