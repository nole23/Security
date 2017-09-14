package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AgentLogs")
@JsonIgnoreProperties(value = { "createAt" }, allowGetters = true)
@CompoundIndexes({ @CompoundIndex(name = "agent_idx", def = "{'time': 1, 'id' : 1}", unique = true) })
public class AgentLogs {

	@Id
	private String id; 				// Id loga
	private Long agent; 			// id agenta na kom se desio log
	private String host; 			// host sa kog dolazi lo
	private String type; 			// tip loga

	@Enumerated(EnumType.STRING)
	private PlatformType platform; 	// platforma sa koje dolazi log Win Lin ...
	private Date time; 				// vreme desavanja loga u Date formatu
	private ErrorLog errorLog; 		// informacije o logu

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlatformType getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformType platform) {
		this.platform = platform;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public ErrorLog getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(ErrorLog errorLog) {
		this.errorLog = errorLog;
	}

}
