package com.app.dto;

import java.util.Date;

import com.app.model.FileLog;

public class FileLogDTO {

	private Long id;

	private String message;

	private String source;

	private int time;

	private int serverTime;

	private Date date;

	private String agentId;

	private int count;

	private String tagName;

	private int logLevel;

	private String platform;

	public FileLogDTO() {
	}

	public FileLogDTO(Long id, String message, String source, int time, int serverTime, Date date, String agentId,
			int count, String tagName, int logLevel, String platform) {
		super();
		this.id = id;
		this.message = message;
		this.source = source;
		this.time = time;
		this.serverTime = serverTime;
		this.date = date;
		this.agentId = agentId;
		this.count = count;
		this.tagName = tagName;
		this.logLevel = logLevel;
		this.platform = platform;
	}

	public FileLogDTO(FileLog fileLog) {
		this.id = fileLog.getId();
		this.message = fileLog.getMessage();
		this.source = fileLog.getSource();
		this.time = fileLog.getTime();
		this.serverTime = fileLog.getServerTime();
		this.date = fileLog.getDate();
		this.agentId = fileLog.getAgentId();
		this.count = fileLog.getCount();
		this.tagName = fileLog.getTagName();
		this.logLevel = fileLog.getLogLevel();
		this.platform = fileLog.getPlatform();
	}

	public FileLog getFileLog() {
		FileLog fileLog = new FileLog();

		fileLog.setMessage(this.message);
		fileLog.setSource(this.source);
		fileLog.setTime(this.time);
		fileLog.setServerTime(this.serverTime);
		fileLog.setDate(this.date);
		fileLog.setAgentId(this.agentId);
		fileLog.setCount(this.count);
		fileLog.setTagName(this.tagName);
		fileLog.setLogLevel(this.logLevel);
		fileLog.setPlatform(this.platform);

		return fileLog;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getServerTime() {
		return serverTime;
	}

	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
