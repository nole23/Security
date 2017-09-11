package com.app.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ErrorLog {
	
	private LevelLog logLevel; 	// tip loga da li je fatal error ili nesto
	private String type; 		// tip loga
	private String error; 		// ispis errora
	private Date time; 			// vreme desavanja loga u Date formatu

	public LevelLog getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LevelLog logLevel) {
		this.logLevel = logLevel;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
