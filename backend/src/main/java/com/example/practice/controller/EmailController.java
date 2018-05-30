package com.example.practice.controller;

import javax.validation.Valid;

import com.example.practice.model.EmailObject;
import com.example.practice.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;

	@Autowired
	EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/mail/send")
    public int createMail(@RequestBody @Valid EmailObject emailObject) {
		System.out.println(emailObject.toString());
        return emailService.sendHtmlMessage(
			emailObject.getTo(), 
			emailObject.getSubject(), 
			emailObject.getText()
		);
    }
}
