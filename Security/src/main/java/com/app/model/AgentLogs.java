package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AgentLogs")
@JsonIgnoreProperties(value = { "createAt" }, allowGetters = true)
@CompoundIndexes({ @CompoundIndex(name = "agent_idx", def = "{'info.error.logLevel': 1, 'id' : 1}", unique = true) })
public class AgentLogs {

	@Id
	private String  id; 		// Id loga
	private InfoLog info; 		// informacije o logu
	private Long agent; 		// id agenta na kom se desio log
	private String host; 		// host sa kog dolazi log

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InfoLog getInfo() {
		return info;
	}

	public void setInfo(InfoLog info) {
		this.info = info;
	}

	public Long getAgent() {
		return agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	

}
