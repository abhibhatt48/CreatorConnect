package com.example.creatorconnectbackend.services;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceTest {

    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(javaMailSender);
    }

    @Test
    public void testSendEmail() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        emailService.sendEmail(to, subject, text);

        verify(javaMailSender).send(any(SimpleMailMessage.class));
    }
    
    @Test
    public void testSendEmail_ExceptionThrown() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        doThrow(MailSendException.class).when(javaMailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail(to, subject, text);

        verify(javaMailSender).send(any(SimpleMailMessage.class));
    }
}
