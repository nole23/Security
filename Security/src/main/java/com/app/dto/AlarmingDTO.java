package com.app.dto;

import java.util.Date;

import com.app.model.Alarming;

public class AlarmingDTO {

	private Long id;
	private String message;
	private Date date;
	private AgentDTO agentDTO;

	public AlarmingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmingDTO(Long id, String message, Date date, AgentDTO agentDTO) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
		this.agentDTO = agentDTO;
	}

	public AlarmingDTO(Alarming al) {
		this.id = al.getId();
		this.message = al.getMessage();
		this.date = al.getDate();
		if (al.getAgents() != null)
			this.agentDTO = new AgentDTO(al.getAgents());
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

	public AgentDTO getAgentDTO() {
		return agentDTO;
	}

	public void setAgentDTO(AgentDTO agentDTO) {
		this.agentDTO = agentDTO;
	}

}
