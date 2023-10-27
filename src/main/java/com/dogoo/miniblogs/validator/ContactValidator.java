package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    private static final String NAME_REQUIRED = "Name is required";
    private static final String EMAIL_REQUIRED = "Email is required";
    private static final String MESSAGE_REQUIRED = "Message is required";

    public void validateAddContact(Contact contact) {
        checkRequiredField(contact.getName(), NAME_REQUIRED);
        checkRequiredField(contact.getEmail(), EMAIL_REQUIRED);
        checkRequiredField(contact.getMessage(), MESSAGE_REQUIRED);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }
}
