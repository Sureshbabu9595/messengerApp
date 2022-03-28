package com.message.messenger.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.message.messenger.response.MessengerRESTResponse;
import com.message.messenger.validation.exception.JSONSchemaValidationException;

@ControllerAdvice
public class MessengerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessengerExceptionHandler.class);
	
	@ExceptionHandler(JSONSchemaValidationException.class)
	@ResponseBody
	public Object handleException(final JSONSchemaValidationException jsonSchemaValidationException) {
		if(LOGGER.isErrorEnabled()) {
			LOGGER.error("JSON Schema Validation Error");
		}
		MessengerRESTResponse<String> restResponse = new MessengerRESTResponse<>();
		restResponse.setError(true);
		restResponse.setResponse(jsonSchemaValidationException.getStatusMessage().toString());
		return restResponse;
	}

}
