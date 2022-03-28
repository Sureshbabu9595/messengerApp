package com.message.messenger.validation.exception;

public enum StatusTypeEnum {
	INFORMATION("Info"), WARNING("Warn"), ERROR("error");

	StatusTypeEnum(String type) {
		this.type = type;
	}

	private final String type;

	public String getType() {
		return type;
	}
}
