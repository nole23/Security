package com.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Alarm {

	@Id
	@GeneratedValue
	private Long id; // id
	private Date time; // vreme okidanja alarma
	private int count; // broj podesavanja

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	private boolean resolver = false;
	private String userResolver;
	private Date firstAlarm;
	private Date lastAlarm;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AlarmDef alarmDef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isResolver() {
		return resolver;
	}

	public void setResolver(boolean resolver) {
		this.resolver = resolver;
	}

	public String getUserResolver() {
		return userResolver;
	}

	public void setUserResolver(String userResolver) {
		this.userResolver = userResolver;
	}

	public Date getFirstAlarm() {
		return firstAlarm;
	}

	public void setFirstAlarm(Date firstAlarm) {
		this.firstAlarm = firstAlarm;
	}

	public Date getLastAlarm() {
		return lastAlarm;
	}

	public void setLastAlarm(Date lastAlarm) {
		this.lastAlarm = lastAlarm;
	}

	public AlarmDef getAlarmDef() {
		return alarmDef;
	}

	public void setAlarmDef(AlarmDef alarmDef) {
		this.alarmDef = alarmDef;
	}

}
