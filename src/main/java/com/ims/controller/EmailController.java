package com.ims.controller;

import com.ims.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public void sendEmail() throws Exception {
        emailService.sendEmail("agonademaj24@gmail.com", "Subject test", "Content test");
    }
}
