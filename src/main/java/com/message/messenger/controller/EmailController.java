package com.message.messenger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.message.messenger.dto.EmailDTO;
import com.message.messenger.request.EmailRequest;
import com.message.messenger.response.EmailResponse;
import com.message.messenger.service.IEmailService;
import com.message.messenger.validation.EnableJSONSchemaValidation;
import com.message.messenger.vo.MessageVO;

@Controller
public class EmailController extends BaseEmailController {

	private static Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private IEmailService emailService;

	@PostMapping("/sendEmail")
	@ResponseBody
	@EnableJSONSchemaValidation
	public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest, HttpServletRequest httpServletRequest) {
		LOGGER.info("Entering.. sendEmail with request::{}", emailRequest);
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.toDTO(emailRequest);
		boolean sendStatus = emailService.sendEmail(emailDTO);
		EmailResponse returnValue = new EmailResponse();
		if (sendStatus) {
			returnValue.setCode("MSG100");
			returnValue.setStatus("SUCCESS");
			returnValue.setEmailAddress(emailRequest.getEmailAddress());
			returnValue.setEmailContent(emailRequest.getEmailContent());
		} else {
			returnValue.setCode("MSG400");
			returnValue.setStatus("FAILURE");
		}
		return returnValue;

	}

	@GetMapping("/getMessages")
	@ResponseBody
	public List<EmailResponse> getMessages(HttpServletRequest httpServletRequest) {
		List<EmailResponse> returnValue = new ArrayList<>();
		List<MessageVO> messages = emailService.getMessages();
		messages.stream().filter(Objects::nonNull).forEach(
				message -> returnValue.add(new EmailResponse(message.getEmailAddress(), message.getEmailContent())));
		return returnValue;

	}

}
