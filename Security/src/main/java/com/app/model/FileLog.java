package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileLog {
	@Id
	@GeneratedValue
	private Long Id;

	@Column
	private String message;

	@Column
	private String source;

	@Column
	private int time;

	@Column
	private int serverTime;

	@Column
	private Date date;

	@Column
	private String agentId;

	@Column
	private int count;

	@Column
	private String tagName;

	@Column
	private int logLevel;

	@Column
	private String platform;

	public FileLog() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getServerTime() {
		return serverTime;
	}

	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "FileLog [Id=" + Id + ", message=" + message + ", source=" + source + ", time=" + time + ", serverTime="
				+ serverTime + ", date=" + date + ", agentId=" + agentId + ", count=" + count + ", tagName=" + tagName
				+ ", logLevel=" + logLevel + ", platform=" + platform + "]";
	}

}
