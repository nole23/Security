package com.app.dto;

import java.util.Date;

import com.app.model.Alarming;

public class AlarmingDTO {

	private Long id;
	private String message;
	private Date date;
	

	public AlarmingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmingDTO(Long id, String message, Date date) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
		
	}

	public AlarmingDTO(Alarming al) {
		this.id = al.getId();
		this.message = al.getMessage();
		this.date = al.getDate();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
