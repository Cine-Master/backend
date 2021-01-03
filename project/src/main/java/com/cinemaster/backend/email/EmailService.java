package com.cinemaster.backend.email;

import com.cinemaster.backend.data.dto.TicketDto;

public interface EmailService {

    String EMAIL_ADDRESS = "cinemaster.ricca@gmail.com";

    void sendTicketEmail(String to, TicketDto ticketDto, String pathToAttachment);

}
