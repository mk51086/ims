package com.ims.service;

import java.io.IOException;

public interface EmailService {
    String sendEmail(String to, String subject, String content) throws IOException;
}
