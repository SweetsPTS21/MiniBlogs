package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.mock.ContactData;
import com.dogoo.miniblogs.model.Contact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactValidatorTest {

    @InjectMocks
    ContactValidator validator;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateAddContact() {
        validator.validateAddContact(ContactData.mockContact());
    }

    @Test
    public void validateAddContactShowExceptionWhenRequiredFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Name is required");

        Contact contact = ContactData.mockContact();
        contact.setName("");

        validator.validateAddContact(contact);
    }
}
