package com.message.messenger.dto;

import com.message.messenger.request.EmailRequest;

public class EmailDTO {

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

	public void toDTO(EmailRequest emailRequest) {
		this.emailAddress = emailRequest.getEmailAddress();
		this.emailContent = emailRequest.getEmailContent();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailDTO [emailAddress=").append(emailAddress).append(", emailContent=").append(emailContent)
				.append("]");
		return builder.toString();
	}

	public EmailDTO() {
		super();
	}
}
