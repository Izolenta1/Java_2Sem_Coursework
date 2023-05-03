package com.example.courseWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String messageText){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("odnorazovaya33@yandex.ru");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageText);

        this.emailSender.send(message);
        System.out.println("Email successfully sent!");
    }
}
