package com.dogoo.miniblogs.mock;

import com.dogoo.miniblogs.model.Contact;

public class ContactData {

    public static String NAME = "This is name";
    public static String EMAIL = "This is email";
    public static String MESSAGE = "This is message";

    public static Contact mockContact() {
        Contact contact = new Contact();
        contact.setName(NAME);
        contact.setEmail(EMAIL);
        contact.setMessage(MESSAGE);
        return contact;
    }
}
