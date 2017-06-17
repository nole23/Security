package agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;

public class AgentLinux extends Monitor {

	private String password;
	private String ip_address;
	private String server_port;
	private String ID;
	private String type;
	private String username;
	private File myfile;
	private long filep;
	private long time;

	/*
	 * log structure : {month day timestamp computer_name proccess_name message
	 * }
	 */

	private String month;
	private String day;
	private long timestamp;
	private String computerName;
	private String proccesName;
	private String message;

	AgentLinux(JSONObject config) {

		super(config);
		time = (long) config.get("sleep");
		password = (String) config.get("password");
		ip_address = (String) config.get("ip_address");
		server_port = (String) config.get("server_port");
		ID = (String) config.get("ID");
		type = (String) config.get("type");
		username = (String) config.get("username");

		myfile = new File((String) config.get("file_url"));
		filep = 0;

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
			}
		});
	}

	@Override
	public void run() {
		boolean active = true;

		while (active) {
			// check for log files
			RandomAccessFile accessFile;

			try {

				accessFile = new RandomAccessFile(myfile, "r");
				// get the length of the file
				long myFileLength = accessFile.length();
				if (myFileLength > filep) {
					// go throuth file from filep location to the end
					accessFile.seek(filep);
					byte[] outputByte = new byte[(int) (myFileLength - filep)];
					// read the bytes and then convert it to string
					accessFile.readFully(outputByte);
					String logStr = new String(new String(outputByte, StandardCharsets.US_ASCII));
					System.out.println(logStr);

					// set pointer to given length
					filep = myFileLength;
				} else if (filep > myFileLength) {
					filep = 0;
				}
				accessFile.close();
				Thread.sleep(time);

			} catch (FileNotFoundException e) {
				System.out.println(myfile.getAbsolutePath()+"doesn't exists!");
				active = false;
			} catch (IOException e) {
				e.printStackTrace();
				active = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
				active = false;
			}

		}
	}
}
