package com.example.practice.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender emailSender;

	@Autowired
	EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("noreply@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	public void sendHtmlMessage(String to, String subject, String text) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("noreply@gmail.com");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			emailSender.send(message);
		} catch (MessagingException exception) {
			exception.printStackTrace();
		}
	}
}
