package com.message.messenger.validation.exception;

public class InvalidStatusMessage extends StatusMessage {

	public InvalidStatusMessage(String message) {
		super();
		this.setCode("JSON-SCHEMA-VALIDATION-FAILED");
		this.setType(StatusTypeEnum.ERROR);
		this.setMessage(message);
	}
}
