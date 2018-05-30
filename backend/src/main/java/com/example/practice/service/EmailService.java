package com.example.practice.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	int sendHtmlMessage(String to, String subject, String text);
}
