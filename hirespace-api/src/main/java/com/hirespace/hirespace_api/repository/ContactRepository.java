package com.hirespace.hirespace_api.repository;

import com.hirespace.hirespace_api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
