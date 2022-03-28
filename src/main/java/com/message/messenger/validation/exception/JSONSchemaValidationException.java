package com.message.messenger.validation.exception;

@SuppressWarnings("serial")
public class JSONSchemaValidationException extends Exception {

	private transient StatusMessage statusMessage;
	public JSONSchemaValidationException(String processingReport) {
		super();
		this.setStatusMessage(new InvalidStatusMessage(processingReport));
	}
	public StatusMessage getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(StatusMessage statusMessage) {
		this.statusMessage = statusMessage;
	}
}
