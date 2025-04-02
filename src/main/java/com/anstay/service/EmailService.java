package com.anstay.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

    public void sendEmailWithTemplate(String to, String subject, String name, String content) throws MessagingException {
        String htmlTemplate = """
                <div style="font-family: Arial, sans-serif; padding: 20px;">
                    <h2>Xin chào %s,</h2>
                    <div style="margin: 20px 0;">
                        %s
                    </div>
                    <p>Trân trọng,<br/>Anstay Team</p>
                </div>
                """.formatted(name, content);

        sendEmail(to, subject, htmlTemplate);
    }
}