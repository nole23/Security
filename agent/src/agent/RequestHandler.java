package agent;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestHandler {

	static String filePath = "configs.json";

	static JSONParser pareser = new JSONParser();

	static String result = null;
	private static boolean running = false;
	private static long startTime = 0;
	private static long stopTime = 0;
	
	@SuppressWarnings("unchecked")
	public static int Send(AgentDTO agent) throws FileNotFoundException, IOException, ParseException {

		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		
		
		
		String url = "https://" + config.get("Ip_address") + ":" + config.get("server_port") + "/api/agent/";
		
		System.setProperty("javax.net.ssl.trustStore", "./keystore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "stefan");

		URL connect = new URL(url);

		HttpsURLConnection con = null;
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
		con = (HttpsURLConnection) connect.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=utf8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", result);
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		JSONObject agent1 = new JSONObject(); 
		
		
		agent1.put("iD", agent.getiD());
		agent1.put("recordNumber", agent.getRecordNumber());
		agent1.put("logType", agent.getLogType());
		agent1.put("timeLog", agent.getTimeLog()); 
		agent1.put("sourceLog", agent.getSourceLog());
		agent1.put("computerName", agent.getComputerName()); 
		agent1.put("messages", agent.getMessages());
		agent1.put("recordNumber", agent.getRecordNumber()); 
		agent1.put("type", agent.getType());
		
		wr.writeBytes(agent1.toString());
		wr.flush(); 
		wr.close();
		
		int status = con.getResponseCode();
		if(status!=200)
			return -1;
		
		return 2;
	}

	/**
	 * Metoda koja nam omogucava konekciju sa serverom
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public synchronized static boolean authenticate() throws IOException, ParseException {

		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		String url = "https://" + config.get("Ip_address") + ":" + config.get("server_port") + "/api/user/login";

		// System.out.println(url);

		System.setProperty("javax.net.ssl.trustStore", "./keystore.p12");
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

		StringBuffer sb = new StringBuffer();
		
		is = new BufferedInputStream(con.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String inputLine = "";
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		result = sb.toString();
		
		if(status != 200)
			return false;
		
		return true;

	}
}
