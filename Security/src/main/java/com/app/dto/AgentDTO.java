package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Agents;
import com.app.model.Alarming;

public class AgentDTO {

	private Long id;
	private String nameBot;
	private String ipAddress;
	private String password;
	private String agentId;
	private Set<AlarmingDTO> alarmingDTO;

	public AgentDTO(Long id, String nameBot, String ipAddress, String password, String agentId,
			Set<AlarmingDTO> alarmingDTO) {
		super();
		this.id = id;
		this.nameBot = nameBot;
		this.ipAddress = ipAddress;
		this.password = password;
		this.agentId = agentId;
		this.alarmingDTO = alarmingDTO;
	}

	public AgentDTO(Agents agents) {

		this.id = agents.getId();
		this.nameBot = agents.getNameBot();
		this.ipAddress = agents.getIpAddress();
		this.password = agents.getPassword();
		if (agents.getAlarming() != null) {
			this.alarmingDTO = new HashSet<AlarmingDTO>();
			for (Alarming al : agents.getAlarming()) {
				this.alarmingDTO.add(new AlarmingDTO(al));
			}
		}
		this.agentId = agents.getAgentId();
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

	public Set<AlarmingDTO> getAlarmingDTO() {
		return alarmingDTO;
	}

	public void setAlarmingDTO(Set<AlarmingDTO> alarmingDTO) {
		this.alarmingDTO = alarmingDTO;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

}
