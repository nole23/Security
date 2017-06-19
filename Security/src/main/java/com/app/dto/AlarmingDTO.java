package com.app.dto;

import com.app.model.Alarming;

public class AlarmingDTO {

	private Long id;
	private String typeLog;
	private int countLog;
	private int countTime;
	private String prioritet;

	public AlarmingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmingDTO(Long id, String typeLog, int countLog, int countTime, String prioritet) {
		super();
		this.id = id;
		this.typeLog = typeLog;
		this.countLog = countLog;
		this.countTime = countTime;
		this.prioritet = prioritet;
	}

	public AlarmingDTO(Alarming a) {
		this.id = a.getId();
		this.typeLog = a.getTypeLog();
		this.countLog = a.getCountLog();
		this.countTime = a.getCountTime();
		this.prioritet = a.getPrioritet();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeLog() {
		return typeLog;
	}

	public void setTypeLog(String typeLog) {
		this.typeLog = typeLog;
	}

	public int getCountLog() {
		return countLog;
	}

	public void setCountLog(int countLog) {
		this.countLog = countLog;
	}

	public int getCountTime() {
		return countTime;
	}

	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}

	public String getPrioritet() {
		return prioritet;
	}

	public void setPrioritet(String prioritet) {
		this.prioritet = prioritet;
	}

}
