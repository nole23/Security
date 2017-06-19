package agent;

import java.io.File;
import java.util.Date;

import org.json.simple.JSONObject;

import com.sun.jna.platform.win32.Advapi32Util.EventLogIterator;
import com.sun.jna.platform.win32.Advapi32Util.EventLogRecord;

public class AgentWindows extends Monitor {

	// Konfiguracije
	private long time;
	private String ID;
	private String type;

	private String username;

	// OVO SE SALJE NA SERVER SVE + type iz konfiguracije
	// KOJI NAM GOVORI DA LI JE LINUX WINDOWS ... RADI LAKSEG CUVANJA
	// A PODACI O AGENTU CE BITI U USER_LOGIN KADA SE LOGUJE
	// TAKO DA PAZI I NJIH DA ISKORISTI

	private String recordNumber;
	private String logType;
	private String timeLog;
	private int YYYY;
	private int MM;
	private int DD;
	private int HH;
	private int min;
	private int ss;
	private String sourceLog;
	private String computerName;
	private String messages;

	AgentWindows(JSONObject config) {
		super(config);

		
		String t = (String) config.get("sleep");
		time = Long.parseLong(t);
		ID = (String) config.get("ID");
		type = (String) config.get("type");
		

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
			}
		});
	}

	@Override
	public void run() {
		boolean start = true;

		String sourceName = "System";

		int id = 0;
		int idFirst = 0;
		while(start) {
			try{
				
				EventLogIterator log = new EventLogIterator(sourceName);
				while (log.hasNext()) {
					EventLogRecord rec = log.next();

					Date toDay = new Date();
					Date logDate = new Date();

					int dayToDay = toDay.getDate();
					int hoursToDay = toDay.getHours();

					logDate.setTime(rec.getRecord().TimeWritten.intValue() * 1000L);

					int logToDay = logDate.getDate();
					int logHoursDay = logDate.getHours();

					recordNumber = String.valueOf(rec.getRecordNumber());
					logType = rec.getType().toString();
					sourceLog = rec.getSource();
					YYYY = logDate.getYear();
					MM = logDate.getMonth();
					DD = logDate.getDate();
					HH = logDate.getHours();
					min = logDate.getMinutes();
					ss = logDate.getSeconds();
					

					if(dayToDay >= logToDay) {
						if((hoursToDay-1) <= logHoursDay) {
							
							
							String result = jWMI.getWMIValue("Select * from Win32_NTLogEvent where LogFile='"+sourceName+"' and RecordNumber="+rec.getRecordNumber(), "ComputerName, Message");

							int computerId = result.indexOf("\r\n");

							// System.out.println(result);
							if (computerId != -1) {

								computerName = result.substring(0, computerId);
								messages = result.substring(computerId + 2, result.length());
							} else {

								computerName = result;
								messages = "";
							}

							
							//Ovo je struktura koja je iscitana iz logFajla
							String sendLog = "RecordNumber: " + recordNumber + " | logType: " + logType + " | timeLog: " + timeLog 
									+ " | sourceLog: " + sourceLog + " | coumputerName: " + computerName + " | messages: " + messages 
									+ " | type: " + type;
							

							idFirst = Integer.parseInt(recordNumber);
							if(id < idFirst){
								
								AgentDTO agent = new AgentDTO();
								
								agent.setiD(ID);
								agent.setRecordNumber(recordNumber);
								agent.setLogType(logType);
								agent.setYYYY(YYYY);
								agent.setMM(MM);
								agent.setDD(DD);
								agent.setHH(HH);
								agent.setMin(min);
								agent.setSs(ss);
								agent.setSourceLog(sourceName);
								agent.setComputerName(computerName);
								agent.setMessages(messages);
								agent.setType(type);
								
								int b = RequestHandler.Send(agent);
								
								if(b == -1) {
									//ukoliko ne moze da posalje na server mora 
									//sacuvati negde u lokalu dok ne bude mogao da salje
									System.out.println("Ne radi");
									id = idFirst;
								} else {
									System.out.println("Sacuvano");
									id = idFirst;
								}
							}
							
							
						}
					}
				}
				Thread.sleep(time);
			} catch (Exception e) {
				e.printStackTrace();
				start = false;
			}
		}
	}
}
