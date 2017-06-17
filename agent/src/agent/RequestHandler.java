package agent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestHandler {
	
	
	static String filePath = "configs.json";
	
	static JSONParser pareser = new JSONParser();
	

	public static int Send(AgentDTO agent) throws FileNotFoundException, IOException, ParseException {
		
		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		authenticate();
		
		
		return -1;
	}
	
	public synchronized static void authenticate() throws IOException, ParseException {
		
		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		String url = "https://" +  config.get("Ip_address") + ":" + config.get("server_port");

		URL connect = new URL(url);
		HttpURLConnection con = null;
		con = (HttpsURLConnection) connect.openConnection();
		
		System.out.println(con);
		
	}
}
