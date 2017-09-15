package agent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONObject;

public class Firewall extends Monitor {


	@SuppressWarnings("unused")
	private long time;
	@SuppressWarnings("unused")
	private String ID;
	@SuppressWarnings("unused")
	private String type;
	
	Firewall(JSONObject config) {

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
		
			for(int i=0; i<300; i++){
				try{
					Random r = new Random();
					int random = r.nextInt(2 + 1);	//3
					int rand = r.nextInt(6 + 1);	//7
					int ra = r.nextInt(5 + 1);		//6
					
					Long[] agenti = {(long) 6, (long) 7, (long) 8};
					String[] hostovi = {"localhost", "192.168.1.1", "www.sbz.com"};
					String[] tipovi = {"LOGIN", "REPORTE", "FAILER", "SAVE", "DELETE", "UPDATE", "REGISTRATION"};
					String[] platforma = {"WINDOWS", "LINUX", "FIREWAL"};
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					String date = sdf.format(new Date());
					System.out.println(new Date().getTime());
					
					
					String[] errori = {"failer credincial", "nesto", "sistem down", "not posible delete", "not posible update", "failer registration"};
					String[] level = {"FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE"};
					String[] message = {"samting went wrong", "wrong login atempt", "not well informed", "adding new plagin has failed", "programs trace state system", ""};
				
					AgentDTO agentDTO = new AgentDTO();
					agentDTO.setAgent(agenti[random]);
					agentDTO.setHost(hostovi[random]);
					agentDTO.setType(tipovi[rand]);
					agentDTO.setPlatform(platforma[random]);
					agentDTO.setTime(date);
					
					ErrorLog errorLog = new ErrorLog();
					errorLog.setError(errori[ra]);
					errorLog.setLogLevel(level[ra]);
					errorLog.setMessage(message[ra]);
					agentDTO.setErrorLog(errorLog);
					
					int b = RequestHandler.Send(agentDTO);
					
					if(b == -1) {
						System.out.println("Ne radi");
					} else {
						System.out.println("Sacuvano");
					}
					Thread.sleep(500);
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		
		
	}
}
