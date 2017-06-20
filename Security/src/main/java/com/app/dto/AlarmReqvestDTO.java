package com.app.dto;

public class AlarmReqvestDTO {

	private int id;
	private Long idAgenta;
	private Long idAlarma;
	private int agentSize;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getIdAgenta() {
		return idAgenta;
	}

	public void setIdAgenta(Long idAgenta) {
		this.idAgenta = idAgenta;
	}

	public Long getIdAlarma() {
		return idAlarma;
	}

	public void setIdAlarma(Long idAlarma) {
		this.idAlarma = idAlarma;
	}

	public int getAgentSize() {
		return agentSize;
	}

	public void setAgentSize(int agentSize) {
		this.agentSize = agentSize;
	}

}
