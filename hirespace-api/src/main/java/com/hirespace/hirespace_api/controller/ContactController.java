package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.Contact;
import com.hirespace.hirespace_api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public String saveContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return "Message saved!";
    }
}
