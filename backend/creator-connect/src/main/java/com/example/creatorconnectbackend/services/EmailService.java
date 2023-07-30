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

@Service
public class EmailService {

    // Instance of JavaMailSender which is Spring's interface for sending mails
    private JavaMailSender javaMailSender;

    // Logger instance for logging purposes
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    // Constructor injecting the JavaMailSender dependency
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

        // Creating a new simple email message
        SimpleMailMessage message = new SimpleMailMessage();

        // Setting the recipient, subject, and content of the email
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            // Using JavaMailSender to send the constructed email
            javaMailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MailException e) {
            // Handling any exceptions that arise during the email sending process
            logger.error("Failed to send email to {}", to, e);
        }
    }
}
