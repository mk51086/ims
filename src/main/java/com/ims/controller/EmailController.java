package com.ims.controller;

import com.ims.service.impl.SendGridServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private SendGridServiceImpl sendGridService;

    @PostMapping("/send-email")
    public void sendEmail() throws Exception {
        sendGridService.sendEmail("agonademaj24@gmail.com", "Subject test", "Content test");
    }
}
