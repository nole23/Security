package agent;

public class AgentDTO {

	private String iD;
	private String agent_id;
	private String recordNumber;
	private String logType;
	private int YYYY;
	private int MM;
	private int DD;
	private int HH;
	private int min;
	private int ss;
	private String sourceLog;
	private String computerName;
	private String messages;
	private String type;

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(String iD, String recordNumber, String agent_id, String logType, String sourceLog,
			String computerName, String messages, String type, int YYYY, int MM, int DD, int HH, int min, int ss) {
		super();
		this.iD = iD;
		this.agent_id = agent_id;
		this.recordNumber = recordNumber;
		this.logType = logType;
		this.YYYY = YYYY;
		this.MM = MM;
		this.DD = DD;
		this.HH = HH;
		this.min = min;
		this.ss = ss;
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

	public int getYYYY() {
		return YYYY;
	}

	public void setYYYY(int yYYY) {
		YYYY = yYYY;
	}

	public int getMM() {
		return MM;
	}

	public void setMM(int mM) {
		MM = mM;
	}

	public int getDD() {
		return DD;
	}

	public void setDD(int dD) {
		DD = dD;
	}

	public int getHH() {
		return HH;
	}

	public void setHH(int hH) {
		HH = hH;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
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
