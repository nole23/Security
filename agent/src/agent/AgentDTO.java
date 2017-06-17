package agent;

public class AgentDTO {

	private String iD;
	private String agent_id;
	private String recordNumber;
	private String logType;
	private String timeLog;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(String iD, String recordNumber, String agent_id, String logType, String timeLog, String sourceLog,
			String computerName, String messages, String type) {
		super();
		this.iD = iD;
		this.agent_id = agent_id;
		this.recordNumber = recordNumber;
		this.logType = logType;
		this.timeLog = timeLog;
		this.sourceLog = sourceLog;
		this.computerName = computerName;
		this.messages = messages;
		this.type = type;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(String timeLog) {
		this.timeLog = timeLog;
	}

	public String getSourceLog() {
		return sourceLog;
	}

	public void setSourceLog(String sourceLog) {
		this.sourceLog = sourceLog;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

}
