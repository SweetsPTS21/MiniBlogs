package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.api.ContactApiDelegate;
import com.dogoo.miniblogs.model.Contact;
import com.dogoo.miniblogs.repository.IContactRepository;
import com.dogoo.miniblogs.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService implements ContactApiDelegate {

    IContactRepository contactRepository;

    private final ContactValidator contactValidator;

    @Value("${spring.mail.username}")
    private String fromMail;

    private final JavaMailSender javaMailSender;
    public ContactService(IContactRepository contactRepository, ContactValidator contactValidator, JavaMailSender javaMailSender) {
        this.contactRepository = contactRepository;
        this.contactValidator = contactValidator;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ResponseEntity<List<Contact>> getContactInfo() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    @Override
    public ResponseEntity<Contact> createContactInfo(Contact contact) {
        contactValidator.validateAddContact(contact);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contact.getEmail());
        message.setSubject("Thank you for contacting us");
        message.setText("We will contact you soon. Thanks!\n Lolia");
        message.setFrom(fromMail);

        javaMailSender.send(message);
        return ResponseEntity.ok(contactRepository.save(contact));
    }
}
