package com.cinemaster.backend.controller;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.dto.ShowDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventCreationParams {

    private ShowDto show;

    private RoomDto room;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<LocalTime> startTimes;

    public ShowDto getShow() {
        return show;
    }

    public void setShow(ShowDto show) {
        this.show = show;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<LocalTime> getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(List<LocalTime> startTimes) {
        this.startTimes = startTimes;
    }
}
