package agent;

import java.util.Date;

public class AgentDTO {

	private String id;
	private Long agent;
	private String host;
	private String type;
	private String platform;
	private String time;
	private ErrorLog errorLog;

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(String id, Long agent, String host, String type, String platform, String time, ErrorLog errorLog) {
		super();
		this.id = id;
		this.agent = agent;
		this.host = host;
		this.type = type;
		this.platform = platform;
		this.time = time;
		this.errorLog = errorLog;
	}

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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ErrorLog getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(ErrorLog errorLog) {
		this.errorLog = errorLog;
	}

}
