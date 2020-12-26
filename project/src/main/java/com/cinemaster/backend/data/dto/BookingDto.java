package com.cinemaster.backend.data.dto;

public class BookingDto {

    private Long id;

    private EventDto eventDto;

    private SeatDto seatDto;

    private UserDto userDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventDto getEventDto() {
        return eventDto;
    }

    public void setEventDto(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    public SeatDto getSeatDto() {
        return seatDto;
    }

    public void setSeatDto(SeatDto seatDto) {
        this.seatDto = seatDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
