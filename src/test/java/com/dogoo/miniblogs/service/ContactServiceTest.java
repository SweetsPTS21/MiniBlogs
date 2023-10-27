package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.mock.ContactData;
import com.dogoo.miniblogs.repository.IContactRepository;
import com.dogoo.miniblogs.validator.ContactValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
    @InjectMocks
    ContactService service;

    @Mock
    IContactRepository repository;

    @Mock
    ContactValidator contactValidator;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void getContactInfo() {
        service.getContactInfo();
    }

    @Test
    public void createContactInfo() {
        service.createContactInfo(ContactData.mockContact());
    }

}
