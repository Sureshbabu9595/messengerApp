package com.message.messenger.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessengerRESTResponse<T extends Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean error;
	
	private List<String> errorMessages = new ArrayList<>();
	
	private T response;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public MessengerRESTResponse() {
		super();
	}
	
	
}
