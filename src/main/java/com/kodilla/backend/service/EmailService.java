package com.kodilla.backend.service;

import com.kodilla.backend.domain.Mail;
import com.kodilla.backend.domain.Rent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender javaMailSender;

    @Autowired
    private RentService rentService;


    private SimpleMailMessage createMailMessage(Mail mail) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());

        return simpleMailMessage;
    }

    public void sendSimpleEmail(final Mail mail) {
        LOGGER.info("Preparing to send email.");
        try {
            javaMailSender.send(createMailMessage(mail));
            LOGGER.info("Email has been sent to: " + mail.getTo());
        } catch (MailException e) {
            LOGGER.error("Email failed. ", e.getMessage(), e);
        }
    }


    public void sendWeeklyNotificationToActiveRentals() {
        LOGGER.info("Preparing to send weekly notification emails to all active book rentals.");

        List<Rent> rents = new ArrayList<>();
        rents = rentService.getAllRents();

        for (Rent rent : rents) {
            try {
                String message = "Dear " + rent.getBorrower().getFirstName() + ", please be informed that car: \"" +
                        rent.getCar().getModelId().getCarModel() + "\" is still rented by you." +
                        " When you finish travelling, please return the car to our rental so other customers can enjoy it as well!";

                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setTo("kodilla.rafal.m@gmail.com");
                simpleMailMessage.setSubject("Weekly notification");
                simpleMailMessage.setText(message);

                javaMailSender.send(simpleMailMessage);
                LOGGER.info("Email has been sent to: kodilla.rafal.m@gmail.com");
            } catch (MailException e) {
                LOGGER.error("Error has occured. ", e.getMessage(), e);
            }
        }
    }

}
