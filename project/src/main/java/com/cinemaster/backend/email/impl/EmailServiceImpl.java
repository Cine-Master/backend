package com.cinemaster.backend.email.impl;

import com.cinemaster.backend.data.dto.CouponDto;
import com.cinemaster.backend.data.dto.TicketDto;
import com.cinemaster.backend.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendTicketEmail(String to, TicketDto ticketDto, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_ADDRESS);
            helper.setTo(to);
            String subject = String.format("Prenotazione #%d confermata", ticketDto.getBookingId());
            String text = "In allegato può trovare il biglietto. Si prega di stamparlo ed esporlo all'ingresso prima dell'evento.\nLa ringraziamo per averci scelto!";
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Ticket.pdf", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCouponEmail(String to, Long bookingId, CouponDto coupon) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_ADDRESS);
        message.setTo(to);
        String subject = String.format("Prenotazione #%d eliminata", bookingId);
        String text = String.format("La prenotazione #%d è stata eliminata con successo.\nQuesto è il codice del coupon dal valore di €%.2f:\n%s", bookingId, coupon.getValue(), coupon.getCode());
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
