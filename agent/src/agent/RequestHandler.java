package agent;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestHandler {

	static String filePath = "configs.json";
	
	static String fileAuthentacion = "auth.json";

	static JSONParser pareser = new JSONParser();
	
	@SuppressWarnings("unchecked")
	public static int Send(AgentDTO agent) throws FileNotFoundException, IOException, ParseException {

		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		
		JSONObject auth = (JSONObject) pareser.parse(new FileReader(fileAuthentacion));
		
		System.out.println("nole " + auth.get("jwt"));
		
		String url = "https://" + config.get("Ip_address") + ":" + config.get("server_port") + "/api/agent/logs/"+auth.get("csrf");
		
		System.setProperty("javax.net.ssl.trustStore", "./data/keystore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "stefan");

		URL connect = new URL(url);

		HttpsURLConnection con = null;
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
		con = (HttpsURLConnection) connect.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=utf8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", auth.get("jwt").toString());
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		JSONObject agent1 = new JSONObject();
		JSONObject error = new JSONObject(); 
		
		agent1.put("id", agent.getId());
		agent1.put("agent", agent.getAgent());
		agent1.put("host", agent.getHost());
		agent1.put("type", agent.getType());
		agent1.put("platform", agent.getPlatform());
		agent1.put("time", agent.getTime());
		
		
		error.put("error", agent.getErrorLog().getError());
		error.put("logLevel", agent.getErrorLog().getLogLevel());
		error.put("message", agent.getErrorLog().getMessage());
		
		agent1.put("errorLog", error);
		
		System.out.println(agent1.toJSONString());
		
		wr.writeBytes(agent1.toString());
		wr.flush(); 
		wr.close();
		
		
		int status = con.getResponseCode();

		InputStream is = null;

		StringBuilder  sb = new StringBuilder ();
		
		is = new BufferedInputStream(con.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String inputLine = "";
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		
		//TODO Update csrf
		
		if(status!=200)
			return -1;
		
		return 2;
	}

	/**
	 * Metoda koja nam omogucava konekciju sa serverom
	 * @param config2 
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public synchronized static boolean authenticate(JSONObject config2) throws IOException, ParseException {

		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		String url = "https://" + config.get("Ip_address") + ":" + config.get("server_port") + "/api/agent/login";

		// System.out.println(url);

		System.setProperty("javax.net.ssl.trustStore", "./data/keystore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "stefan");

		URL connect = new URL(url);
		
		HttpsURLConnection con = null;
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
		con = (HttpsURLConnection) connect.openConnection();
		
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=utf8");
	    con.setRequestProperty("Accept", "application/json");
	    
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		JSONObject login = new JSONObject(); 
		String username = (String) config.get("username");
		String password = (String) config.get("password");
		System.out.println(username);
		login.put("username", username);
		login.put("password", password);
		wr.writeBytes(login.toString());
		wr.flush(); 
		wr.close();
		

		int status = con.getResponseCode();

		InputStream is = null;

		StringBuilder  sb = new StringBuilder ();
		
		is = new BufferedInputStream(con.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String inputLine = "";
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		
		JSONObject config1 = (JSONObject) pareser.parse(sb.toString());
		FileWriter fileWriter = new FileWriter(new File(fileAuthentacion));
		fileWriter.write(config1.toJSONString());
		fileWriter.flush();
		
		if(status != 200)
			return false;
		
		return true;

	}
}
