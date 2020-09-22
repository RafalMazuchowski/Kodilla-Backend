package com.kodilla.backend.scheduler;

import com.kodilla.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * MON")
    public void sendWeeklyRentalsNotifications(){
        emailService.sendWeeklyNotificationToActiveRentals();
    }

}
