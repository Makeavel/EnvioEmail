package com.api.envioemail.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.envioemail.model.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) throws MessagingException {

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody());
            helper.setSubject(details.getSubject());

            mailSender.send(mail);
            return "SUCCESS";

        }catch (Exception ex){
            ex.printStackTrace();
            return "ERROR";
        }

    }

    @Override
    public String sendMail(String email ) throws MessagingException {

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setFrom(sender);
            helper.setTo(email);
            helper.setText("Conteudo bolado da mensagem");
            helper.setSubject("Teste de envio autononmo");

            mailSender.send(mail);
            return "SUCCESS";

        }catch (Exception ex){
            //sendMail(email);
            ex.printStackTrace();
            return "ERROR";
        }
    }



    @Override
    public String sendMailWithAttachment(EmailDetails details) {

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            MimeMessageHelper helper = new MimeMessageHelper(mail , true);

            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody());
            helper.setSubject(details.getSubject());
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(mail);
            return "SUCCESS";

        }catch (Exception ex){
            sendMailWithAttachment(details);
            ex.printStackTrace();
            return "ERROR";
        }
    }
}
