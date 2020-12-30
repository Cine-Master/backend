package com.cinemaster.backend.email;

import com.cinemaster.backend.controller.booking.Ticket;

public interface EmailService {

    String EMAIL_ADDRESS = "cinemaster.ricca@gmail.com";

    void sendTicketEmail(String to, Ticket ticket, String pathToAttachment);

}
