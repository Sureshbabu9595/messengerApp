package com.message.messenger.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageVOTest {

	private MessageVO messageVO;

	@BeforeEach
	void setUp() throws Exception {
		messageVO = new MessageVO();
		messageVO.setEmailAddress("sureshbabu8947@gmail.com");
		messageVO.setEmailContent("Greetings!!");
	}

	@AfterEach
	void tearDown() throws Exception {
		messageVO = null;
	}

	@Test
	void testGetEmailAddress() {
		assertNotNull(messageVO);
		assertEquals("sureshbabu8947@gmail.com", messageVO.getEmailAddress());
	}

	@Test
	void testSetEmailAddress() {
		assertNotNull(messageVO);
		messageVO.setEmailAddress("nobody@abc.com");
		assertEquals("nobody@abc.com", messageVO.getEmailAddress());
	}

	@Test
	void testGetEmailContent() {
		assertNotNull(messageVO);
		assertEquals("Greetings!!", messageVO.getEmailContent());
	}

	@Test
	void testSetEmailContent() {
		assertNotNull(messageVO);
		assertNotNull(messageVO.toString());
		messageVO.setEmailContent("Hi There");
		assertEquals("Hi There", messageVO.getEmailContent());
	}
	
	@Test
	void testMessageVOStringString() {
		MessageVO messageVO1 = new MessageVO("sureshbabu8947@gmail.com", "Greetings!!");
		assertNotNull(messageVO1);
		assertNotNull(messageVO1.toString());
		messageVO.setEmailContent("Hi There");
		assertEquals("Hi There", messageVO.getEmailContent());
	}
}
