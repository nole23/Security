package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AlarmDef {

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private LevelLog levelLog; // vrsta alarma
	private int minCount; // minimalan broj za okidanje
	private int maxCount; // maksimalan broj za okidanje
	
	@Enumerated(EnumType.STRING)
	private AlarmLevel alarmLevel; // level alarma HIGH...
	private int timeAlarm; // u datom vremenskom intervalu

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Alarm> alarm = new HashSet<Alarm>();

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

	public Set<Alarm> getAlarm() {
		return alarm;
	}

	public void setAlarm(Set<Alarm> alarm) {
		this.alarm = alarm;
	}

}
