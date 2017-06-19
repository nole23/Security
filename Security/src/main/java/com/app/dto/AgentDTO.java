package com.app.dto;

import com.app.model.Agents;

public class AgentDTO {

	private String iD;
	private String recordNumber;
	private String logType;
	private int yyyy;
	private int mm;
	private int dd;
	private int hh;
	private int min;
	private int ss;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(String iD, String recordNumber, String logType, String sourceLog, String computerName,
			String messages, String type, int yyyy, int mm, int dd, int hh, int min,
			int ss) {
		super();
		this.iD = iD;
		this.recordNumber = recordNumber;
		this.logType = logType;
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
		this.hh = hh;
		this.min = min;
		this.ss = ss;
		this.sourceLog = sourceLog;
		this.computerName = computerName;
		this.messages = messages;
		this.type = type;
	}

	public AgentDTO(Agents a) {
		this.recordNumber = a.getRecordNumber();
		this.logType = a.getLogType();
		this.yyyy = a.getYyyy();
		this.mm = a.getMm();
		this.dd = a.getDd();
		this.hh = a.getHh();
		this.min = a.getMin();
		this.ss = a.getSs();
		this.sourceLog = a.getSourceLog();
		this.computerName = a.getComputerName();
		this.messages = a.getMessages();
		this.type = a.getType();

	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
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

	public int getYyyy() {
		return yyyy;
	}

	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getHh() {
		return hh;
	}

	public void setHh(int hh) {
		this.hh = hh;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
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
