package com.cinemaster.backend.email;

public interface EmailService {

    String EMAIL_ADDRESS = "cinemaster.ricca@gmail.com";

    void sendEmail(String to, String subject, String text, String pathToAttachment);

}
