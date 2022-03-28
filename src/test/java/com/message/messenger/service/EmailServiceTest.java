package com.message.messenger.service;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.message.messenger.dao.IEmailDao;
import com.message.messenger.dto.EmailDTO;

@RunWith(MockitoJUnitRunner.class)
class EmailServiceTest {
	
	@InjectMocks
	private EmailService emailService;
	
	@Mock
	private IEmailDao emailDao;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSendEmail() {
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setEmailAddress("test@abccorp.com");
		emailDTO.setEmailContent("Hi There");
		emailService.sendEmail(emailDTO);
	}

	@Test
	void testGetMessages() {
		emailService.getMessages();
	}

}
