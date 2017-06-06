package com.app.dto;

import com.app.model.Agents;

public class AgentDTO {

	private Long id;
	private String nameBot;
	private String ipAddress;
	private String password;

	public AgentDTO(Long id, String nameBot, String ipAddress, String password) {
		super();
		this.id = id;
		this.nameBot = nameBot;
		this.ipAddress = ipAddress;
		this.password = password;
	}

	public AgentDTO(Agents agents) {

		this.id = agents.getId();
		this.nameBot = agents.getNameBot();
		this.ipAddress = agents.getIpAddress();
		this.password = agents.getPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameBot() {
		return nameBot;
	}

	public void setNameBot(String nameBot) {
		this.nameBot = nameBot;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
