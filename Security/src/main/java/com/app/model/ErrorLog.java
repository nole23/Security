package com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ErrorLog {

	private String error; 		// ispis errora
	private LevelLog logLevel; 	// tip loga da li je fatal error ili nesto
	private String message; 	// poruka greske

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public LevelLog getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LevelLog logLevel) {
		this.logLevel = logLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
