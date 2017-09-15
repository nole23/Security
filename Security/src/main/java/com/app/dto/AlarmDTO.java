package com.app.dto;

import java.util.Date;

import com.app.model.Alarm;
import com.app.model.AlarmDef;
import com.app.model.User;

public class AlarmDTO {

	private Long id; // id
	private Date time; // vreme okidanja alarma
	private int count;
	private UserDTO userDTO;
	private boolean resolver = false;
	private String userResolver;
	private Date firstAlarm;
	private Date lastAlarm;
	private AlarmDefDTO alarmDefDTO;

	public AlarmDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmDTO(Long id, Date time, int count, UserDTO userDTO, boolean resolver, String userResolver,
			Date firstAlarm, Date lastAlarm, AlarmDefDTO alarmDefDTO) {
		super();
		this.id = id;
		this.time = time;
		this.count = count;
		this.userDTO = userDTO;
		this.resolver = resolver;
		this.userResolver = userResolver;
		this.firstAlarm = firstAlarm;
		this.lastAlarm = lastAlarm;
		this.alarmDefDTO = alarmDefDTO;
	}
	
	public AlarmDTO(Alarm a) {
		super();
		this.id = a.getId();
		this.time = a.getTime();
		this.count = a.getCount();
		this.resolver = a.isResolver();
		this.userDTO = new UserDTO(a.getUser());
		this.userResolver = a.getUserResolver();
		this.firstAlarm = a.getFirstAlarm();
		this.lastAlarm = a.getLastAlarm();
		this.alarmDefDTO = new AlarmDefDTO(a.getAlarmDef());
	}

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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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

	public AlarmDefDTO getAlarmDefDTO() {
		return alarmDefDTO;
	}

	public void setAlarmDefDTO(AlarmDefDTO alarmDefDTO) {
		this.alarmDefDTO = alarmDefDTO;
	}

}
