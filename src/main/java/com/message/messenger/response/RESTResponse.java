package com.message.messenger.response;

public class RESTResponse {
	
	protected String status;
	
	protected String code;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RESTResponse [status=").append(status).append(", code=").append(code).append("]");
		return builder.toString();
	}
}
