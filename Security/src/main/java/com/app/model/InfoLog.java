package com.app.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "InfoLog")
public class InfoLog {
	
	@Enumerated(EnumType.STRING)
	private PlatformType platform; 	// platforma sa koje dolazi log Win Lin ...
	private String message; 		// poruka koja je greska u pitanju
	private List<ErrorLog> error; 	// podaci o logu koje je iscitao


	public PlatformType getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformType platform) {
		this.platform = platform;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorLog> getError() {
		return error;
	}

	public void setError(List<ErrorLog> error) {
		this.error = error;
	}

}
