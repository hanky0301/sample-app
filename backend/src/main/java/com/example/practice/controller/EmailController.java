package com.example.practice.controller;

import javax.validation.Valid;

import com.example.practice.model.EmailObject;
import com.example.practice.service.EmailService;
import com.example.practice.service.EmailServiceImpl;

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
	EmailController(EmailServiceImpl emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/mail/send")
    public void createMail(@RequestBody @Valid EmailObject emailObject) {
		System.out.println(emailObject.toString());
        emailService.sendHtmlMessage(
			emailObject.getTo(), 
			emailObject.getSubject(), 
			emailObject.getText()
		);
    }
}
