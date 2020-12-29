package com.cinemaster.backend.controller.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Ticket {

    private Long bookingId;

    private String userName;

    private String showName;

    private String roomName;

    private String seat;

    private LocalDate date;

    private LocalTime startTime;

    private Double price;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String generateBarcodeEAN13() {
        Long barcode = Math.abs(hashCode()) % 1000000000000L;
        int length = barcode.toString().length();
        barcode = (long) (barcode * Math.pow(10, 12 - length));
        return barcode.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return bookingId.equals(ticket.bookingId) &&
                userName.equals(ticket.userName) &&
                showName.equals(ticket.showName) &&
                roomName.equals(ticket.roomName) &&
                seat.equals(ticket.seat) &&
                date.equals(ticket.date) &&
                startTime.equals(ticket.startTime) &&
                price.equals(ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, userName, showName, roomName, seat, date, startTime, price);
    }
}
