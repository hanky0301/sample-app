package com.example.practice.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender emailSender;

	@Autowired
	EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public int sendHtmlMessage(String to, String subject, String text) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			emailSender.send(message);
			return 0;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			return -1;
		}
	}
}
