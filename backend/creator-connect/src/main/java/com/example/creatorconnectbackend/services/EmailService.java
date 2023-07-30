package com.example.creatorconnectbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * EmailService class is responsible for sending emails using Spring's JavaMailSender interface.
 * It provides methods to send simple text-based emails to a specified recipient.
 *
 * Functions:
 * 1. sendEmail: Sends a simple email to the specified recipient with the given subject and content.
 *    - Parameters:
 *        - to (String): Email recipient
 *        - subject (String): Subject of the email
 *        - text (String): Content of the email
 *    - Exceptions:
 *        - MailException: If there is an issue while sending the email, it logs an error.
 */

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends a simple email.
     *
     * @param to      Email recipient
     * @param subject Subject of the email
     * @param text    Content of the email
     */
    public void sendEmail(String to, String subject, String text) {
        logger.info("Attempting to send email to {}", to);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MailException e) {
            logger.error("Failed to send email to {}", to, e);
        }
    }
}
