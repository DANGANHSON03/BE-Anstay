package com.anstay.controller;

import com.anstay.entity.Contact;

import com.anstay.service.ContactService;
import com.anstay.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        try {
            Contact createdContact = contactService.createContact(contact);

            // Gửi email thông báo đến admin
            emailService.sendEmailWithTemplate(
                    "anhson22062003xxx@gmail.com",  // Email admin từ application.properties
                    "Thông báo: Có liên hệ mới từ " + contact.getName(),
                    "Admin",
                    String.format("""
                    Thông tin liên hệ mới:
                    <br/><br/>
                    <b>Tên:</b> %s
                    <br/>
                    <b>Email:</b> %s
                    <br/>
                    <b>Số điện thoại:</b> %s
                    <br/>
                    <b>Nội dung:</b> %s
                    <br/><br/>
                    <b>Thời gian:</b> %s
                    """,
                            contact.getName(),
                            contact.getEmail(),
                            contact.getPhone(),
                            contact.getMessage(),
                            contact.getCreatedAt()
                    )
            );

            return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
