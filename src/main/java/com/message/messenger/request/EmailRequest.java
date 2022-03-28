package com.message.messenger.request;

public class EmailRequest extends RESTRequest{
	
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
		builder.append("EmailRequest [emailAddress=").append(emailAddress).append(", emailContent=")
				.append(emailContent).append("]");
		return builder.toString();
	}

	public EmailRequest() {
		super();
	}
}
