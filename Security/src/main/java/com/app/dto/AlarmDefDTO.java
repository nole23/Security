package com.app.dto;

import com.app.model.AlarmDef;
import com.app.model.AlarmLevel;
import com.app.model.LevelLog;

public class AlarmDefDTO {

	private Long id;
	private LevelLog levelLog; // vrsta alarma
	private int minCount; // minimalan broj za okidanje
	private int maxCount;
	private AlarmLevel alarmLevel; // level alarma HIGH...
	private int timeAlarm;

	public AlarmDefDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmDefDTO(Long id, LevelLog levelLog, int minCount, int maxCount, AlarmLevel alarmLevel, int timeAlarm) {
		super();
		this.id = id;
		this.levelLog = levelLog;
		this.minCount = minCount;
		this.maxCount = maxCount;
		this.alarmLevel = alarmLevel;
		this.timeAlarm = timeAlarm;
	}
	
	public AlarmDefDTO(AlarmDef ad) {
		super();
		this.levelLog = ad.getLevelLog();
		this.minCount = ad.getMinCount();
		this.maxCount = ad.getMaxCount();
		this.alarmLevel = ad.getAlarmLevel();
		this.timeAlarm = ad.getTimeAlarm();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LevelLog getLevelLog() {
		return levelLog;
	}

	public void setLevelLog(LevelLog levelLog) {
		this.levelLog = levelLog;
	}

	public int getMinCount() {
		return minCount;
	}

	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public AlarmLevel getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(AlarmLevel alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public int getTimeAlarm() {
		return timeAlarm;
	}

	public void setTimeAlarm(int timeAlarm) {
		this.timeAlarm = timeAlarm;
	}

}
