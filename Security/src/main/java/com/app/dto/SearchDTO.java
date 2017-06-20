package com.app.dto;

public class SearchDTO {

	private String recordNumber;
	private String logType;
	private int dd;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;

	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDTO(String recordNumber, String logType, int dd, String sourceLog, String computerName,
			String messages, String type) {
		super();
		this.recordNumber = recordNumber;
		this.logType = logType;
		this.dd = dd;
		this.sourceLog = sourceLog;
		this.computerName = computerName;
		this.messages = messages;
		this.type = type;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public String getSourceLog() {
		return sourceLog;
	}

	public void setSourceLog(String sourceLog) {
		this.sourceLog = sourceLog;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
