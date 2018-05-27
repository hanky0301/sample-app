package com.example.practice.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);
	void sendHtmlMessage(String to, String subject, String text);
}
