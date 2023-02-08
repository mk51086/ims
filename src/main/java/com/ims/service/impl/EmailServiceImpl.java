package com.ims.service.impl;

import com.ims.service.EmailService;
import com.sendgrid.SendGrid;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String apiKey;

    public String sendEmail(String to, String subject, String content) throws IOException {
        Email from = new Email("im.software.ks@gmail.com");
        Content emailContent = new Content("text/plain", content);
        Mail email = new Mail(from, subject, new Email(to), emailContent);
        SendGrid sg = new SendGrid(apiKey);

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(email.build());
            Response response = sg.api(request);

           if (response.getStatusCode() == 200) {
               return "Email send successfully";
           }

            return response.getBody();

        } catch (IOException ex) {
            throw ex;
        }
    }
}
