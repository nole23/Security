package agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Main {

	static Scanner scanner;
	static String filePath = "configs.json";

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		scanner = new Scanner(System.in);
		File file = new File(filePath);

		if (!file.exists())
			setUp();
		
		JSONParser pareser = new JSONParser();
		
		JSONObject config = (JSONObject) pareser.parse(new FileReader(filePath));
		
		
		boolean status = RequestHandler.authenticate();
		if(status == false){
			System.out.println("Pokusavam da se konektujem na mrezu");
			main(args);
		}
		
		
		if(config.get("type").equals("windows")) {
			
			//TREBA POVEZATI SA AgentWindows
			System.out.println("Ovde ide AgentWindows");
			Thread th = new Thread(new AgentWindows(config));
			th.start();
		} else if (config.get("type").equals("linux")){
			
			System.out.println("Starting linux agent monitoring");
			Thread th = new Thread(new AgentLinux(config));
			th.start();
			
		} else if(config.get("type").equals("firewal")) {
			
			//OVDE IDE FIREVAL
			System.out.println("Ovde ide firewal");
		}
	}

	public static void setUp() {

		String fileInput = null;
		JSONObject config = new JSONObject();
		String[] printStrings = { "Enter agent ID", "Please enter username", "Please enter password",
				"Please enter center Ip address", "Please enter server port", "Please enter type system", "Pleas enter time of sleep" };
		String[] configFields = { "ID", "username", "password", "Ip_address", "server_port", "type", "sleep" };

		for (int i = 0; i < printStrings.length; i++) {

			System.out.println(printStrings[i]);
			fileInput = scanner.nextLine();
			config.put(configFields[i], fileInput);
		}

		// save to file
		try {
			FileWriter fileWriter = new FileWriter(new File(filePath));
			fileWriter.write(config.toJSONString());
			fileWriter.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}