package com.message.messenger.controller;

import com.message.messenger.request.EmailRequest;
/**
 * 
 * @author sureshbabu
 *
 */
public class BaseEmailController {
	
	protected boolean validateEmailRequest(EmailRequest emailRequest) {
		return true;
	}

}
