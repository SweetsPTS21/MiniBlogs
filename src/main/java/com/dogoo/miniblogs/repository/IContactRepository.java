package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IContactRepository extends MongoRepository<Contact, String> {

}
