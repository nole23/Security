package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alarming {

	@Id
	@GeneratedValue
	private Long id;
	private String typeLog; // Tip loga "ERROR" "WARNINF" "INFO"
	private int countLog; // Broj ponavljanja "10"
	private int countTime; // Broj vreme koje se gleda za ponavljanje u
							// sekundama "60"
	private String sourceLog;
	private String prioritet;// Stepen upozorenje "LOW" "MEDIUM" "HEIGHT"

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

	public String getSourceLog() {
		return sourceLog;
	}

	public void setSourceLog(String sourceLog) {
		this.sourceLog = sourceLog;
	}

}
