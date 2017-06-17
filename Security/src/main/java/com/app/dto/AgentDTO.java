package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Agents;
import com.app.model.Alarming;

public class AgentDTO {

	private String iD;
	private String recordNumber;
	private String logType;
	private String timeLog;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;
	private Set<AlarmingDTO> alarmingDTO;

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(String iD, String recordNumber, String logType, String timeLog, String sourceLog,
			String computerName, String messages, String type, Set<AlarmingDTO> alarmingDTO) {
		super();
		this.iD = iD;
		this.recordNumber = recordNumber;
		this.logType = logType;
		this.timeLog = timeLog;
		this.sourceLog = sourceLog;
		this.computerName = computerName;
		this.messages = messages;
		this.type = type;
		this.alarmingDTO = alarmingDTO;
	}

	public AgentDTO(Agents a) {
		this.recordNumber = a.getRecordNumber();
		this.logType = a.getLogType();
		this.timeLog = a.getTimeLog();
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

	public Set<AlarmingDTO> getAlarmingDTO() {
		return alarmingDTO;
	}

	public void setAlarmingDTO(Set<AlarmingDTO> alarmingDTO) {
		this.alarmingDTO = alarmingDTO;
	}

}
