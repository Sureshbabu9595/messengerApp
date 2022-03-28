package com.message.messenger.response;

import java.io.Serializable;

public class EmailResponse extends RESTResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String emailAddress;

	private String emailContent;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailResponse [emailAddress=").append(emailAddress).append(", emailContent=")
				.append(emailContent).append("]");
		return builder.toString();
	}

	public EmailResponse() {
		super();
	}

	public EmailResponse(String emailAddress, String emailContent) {
		super();
		this.emailAddress = emailAddress;
		this.emailContent = emailContent;
	}
}
