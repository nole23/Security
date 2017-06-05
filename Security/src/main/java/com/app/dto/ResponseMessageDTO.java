package com.app.dto;

public class ResponseMessageDTO {

	private String message;

	public ResponseMessageDTO() {
	}

	public ResponseMessageDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
