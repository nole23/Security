package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agents {

	@Id
	@GeneratedValue
	private String iD;
	private String recordNumber;
	private String logType;
	private String timeLog;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;

	@OneToMany(mappedBy = "agents", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Alarming> alarming = new HashSet<Alarming>();

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

	public String getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(String timeLog) {
		this.timeLog = timeLog;
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

	public Set<Alarming> getAlarming() {
		return alarming;
	}

	public void setAlarming(Set<Alarming> alarming) {
		this.alarming = alarming;
	}

	
}
