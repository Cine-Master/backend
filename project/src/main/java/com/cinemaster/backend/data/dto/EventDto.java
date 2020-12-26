package com.cinemaster.backend.data.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDto {

    private Long id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private ShowDto showDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ShowDto getShowDto() {
        return showDto;
    }

    public void setShowDto(ShowDto showDto) {
        this.showDto = showDto;
    }
}
