package com.cinemaster.backend.email;

import com.cinemaster.backend.data.dto.CouponDto;
import com.cinemaster.backend.data.dto.TicketDto;

public interface EmailService {

    String EMAIL_ADDRESS = "cinemaster.ricca@gmail.com";

    void sendTicketEmail(String to, TicketDto ticketDto, String pathToAttachment);

    void sendCouponEmail(String to, Long bookingId, CouponDto coupon);

}
