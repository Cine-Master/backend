package com.cinemaster.backend.pdf;

import com.cinemaster.backend.controller.booking.Ticket;

public interface PdfService {

    String createPdf(Ticket ticket);

}
