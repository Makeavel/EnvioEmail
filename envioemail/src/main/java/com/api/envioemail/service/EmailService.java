package com.api.envioemail.service;

import com.api.envioemail.model.EmailDetails;

import jakarta.mail.MessagingException;

public interface EmailService {
    
    String sendSimpleMail(EmailDetails details) throws MessagingException;

    String sendMail(String email) throws MessagingException;
    
    String sendMailWithAttachment(EmailDetails details);
}
