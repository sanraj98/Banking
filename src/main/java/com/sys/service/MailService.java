package com.sys.service;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmail(String to, String subject, String text) throws MessagingException;

}
