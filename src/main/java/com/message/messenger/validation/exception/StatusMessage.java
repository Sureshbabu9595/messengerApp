package com.message.messenger.validation.exception;

import java.util.Arrays;

public class StatusMessage {
	private StatusTypeEnum type;

	private String code;

	private String message;

	private String[] fields;

	public StatusTypeEnum getType() {
		return type;
	}

	public void setType(StatusTypeEnum type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatusMessage [type=").append(type).append(", code=").append(code).append(", message=")
				.append(message).append(", fields=").append(Arrays.toString(fields)).append("]");
		return builder.toString();
	}
}
