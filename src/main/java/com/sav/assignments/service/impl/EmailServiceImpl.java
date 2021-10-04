package com.sav.assignments.service.impl;

import com.sav.assignments.entity.AppUser;
import com.sav.assignments.service.EmailService;
import com.sav.assignments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;


    @Override
    public void sendMailRegister(AppUser user) {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String confirmationUrl
            = "http://localhost:8080/registrationConfirm.html?token=" + token;

        sendSimpleMessage(user.getEmail(), "Registration Confirmation", confirmationUrl);
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        emailSender.send(email);
    }
}
