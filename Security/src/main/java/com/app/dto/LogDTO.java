package com.app.dto;

import java.util.Date;

import com.app.model.LevelLog;
import com.app.model.PlatformType;

public class LogDTO {

	private String username;
	private String hots;
	private PlatformType platform;
	private String message;
	private LevelLog level;
	private String type;
	private String error;
	private Date date;

	public LogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogDTO(String username, String hots, PlatformType platform, String message, LevelLog level, String type,
			String error, Date date) {
		super();
		this.username = username;
		this.hots = hots;
		this.platform = platform;
		this.message = message;
		this.level = level;
		this.type = type;
		this.error = error;
		this.date = date;
	}

	public LogDTO(LogDTO logOne) {
		// TODO Auto-generated constructor stub
		this.username = logOne.getUsername();
		this.hots = logOne.getHots();
		this.platform = logOne.getPlatform();
		this.message = logOne.getMessage();
		this.level = logOne.getLevel();
		this.type = logOne.getType();
		this.error = logOne.getError();
		this.date = logOne.getDate();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHots() {
		return hots;
	}

	public void setHots(String hots) {
		this.hots = hots;
	}

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

	public LevelLog getLevel() {
		return level;
	}

	public void setLevel(LevelLog level) {
		this.level = level;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
