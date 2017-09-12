package com.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class AlarmDef {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	private int counter = 0;

	@NotNull
	private String message;

	@NotNull
	private Date firstoccurence;

	@NotNull
	private Date lastoccurence;

	@OneToMany(mappedBy = "alarmDef", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Alarm> alarms = new HashSet<Alarm>();

	public AlarmDef() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getFirstoccurence() {
		return firstoccurence;
	}

	public void setFirstoccurence(Date firstoccurence) {
		this.firstoccurence = firstoccurence;
	}

	public Date getLastoccurence() {
		return lastoccurence;
	}

	public void setLastoccurence(Date lastoccurence) {
		this.lastoccurence = lastoccurence;
	}

	public Set<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(Set<Alarm> alarms) {
		this.alarms = alarms;
	}

}
