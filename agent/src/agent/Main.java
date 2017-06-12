package agent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Main {

	static Scanner scanner;
	static String filePath = "configs.json";

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		File file = new File(filePath);

		if (!file.exists())
			setUp();
	}

	public static void setUp() {

		String fileInput = null;
		JSONObject config = new JSONObject();
		String[] printStrings = { "Enter agent ID", "Please enter username", "Please enter password",
				"Please enter center Ip address", "Please enter server port", "Please enter path to log file" };
		String[] configFields = { "ID", "username", "password", "Ip_address", "server_port", "log_path" };

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
