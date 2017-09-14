package agent;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	private String YYYY;
	private int MM;
	private int DD;
	private int HH;
	private int min;
	private int ss;
	private String sourceLog;
	private String computerName;
	private String messages;

	static String filePath = "configs.json";
	
	
	
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

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		boolean start = true;

		String sourceName = "Application";

		int id = 0;
		int idFirst = 0;
		while(start) {
			try{
				
				JSONParser pareser = new JSONParser();
				JSONObject conf = (JSONObject) pareser.parse(new FileReader(filePath));
				
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
					//YYYY = toDay.getYear();
					MM = toDay.getMonth();
					DD = toDay.getDate();
					HH = toDay.getHours();
					min = toDay.getMinutes();
					ss = toDay.getSeconds();

					System.out.println("dosao: " + hoursToDay + " otisao; " + logHoursDay);
					if(dayToDay == logToDay) {
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

							
							//System.out.println(sendLog);
							idFirst = Integer.parseInt(recordNumber);
							if(id < idFirst){
								
								AgentDTO agent = new AgentDTO();
								
								agent.setAgent(Long.parseLong(conf.get("ID").toString()));
								agent.setHost(conf.get("Ip_address").toString());
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
								String date = sdf.format(new Date());
								System.out.println(date);
								agent.setTime(date);
								
								String[] levelLog = {"FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE"};
								Random r = new Random();
								int random = r.nextInt(5 + 1);
								
								String[] tip = {"SAVE", "LOGIN", "AUTHENTICATION", "REGISTRATION", "DELETE", "UPDATE"};
								
								agent.setType(tip[random]);
								agent.setPlatform("WINDOWS");
								
								ErrorLog errorLog = new ErrorLog();
								errorLog.setLogLevel(levelLog[random]);
								errorLog.setError("error message");
								errorLog.setMessage(messages);
								
								agent.setErrorLog(errorLog);
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
