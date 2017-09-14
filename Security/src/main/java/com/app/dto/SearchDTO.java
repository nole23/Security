package com.app.dto;

import java.util.Date;

public class SearchDTO {

	private String agentId;
	private String type;
	private Date startData;
	private Date endData;
	private String platforma;
	private String regular;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartData() {
		return startData;
	}

	public void setStartData(Date startData) {
		this.startData = startData;
	}

	public Date getEndData() {
		return endData;
	}

	public void setEndData(Date endData) {
		this.endData = endData;
	}

	public String getPlatforma() {
		return platforma;
	}

	public void setPlatforma(String platforma) {
		this.platforma = platforma;
	}

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
	}

}
