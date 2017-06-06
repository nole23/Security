package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agents {

	@Id
	@GeneratedValue
	private Long id;
	private String nameBot;
	private String ipAddress;
	private String password;
	private String agentId;

	@OneToMany(mappedBy = "agents", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Alarming> alarming = new HashSet<Alarming>();

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

	public Set<Alarming> getAlarming() {
		return alarming;
	}

	public void setAlarming(Set<Alarming> alarming) {
		this.alarming = alarming;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

}
