package agent;

public class ErrorLog {

	private String error;
	private String logLevel;
	private String message;

	public ErrorLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorLog(String error, String logLevel, String message) {
		super();
		this.error = error;
		this.logLevel = logLevel;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
