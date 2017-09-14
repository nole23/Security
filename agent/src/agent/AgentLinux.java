package agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class AgentLinux extends Monitor {

	private File myfile;
	private long filep;
	private long time;
	private String type;

	private String regex = "([A-Z][a-z]{2}\\s[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2})\\s([a-zA-Z0-9-]+)\\s([a-zA-Z0-9-\\[\\]]+:)\\s(.+)";

	AgentLinux(JSONObject config) {

		super(config);
		String t = (String) config.get("sleep");
		time = Long.parseLong(t);
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
				
				System.out.println("Usao");
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
					//System.out.println(logStr);

					Pattern p=Pattern.compile(regex);
					Matcher m=p.matcher(logStr);
					
					while(m.find()) {
						String line=m.group();
						
						
						if(line.toUpperCase().contains("WARNING")||line.toUpperCase().contains("WARN")) type="Warning";
						if(line.toUpperCase().contains("ERROR") ) type="Error";
						type = "Informational";
						
						/**
						 * m.group(1) - //Jun 18 15:35:01//
						 * m.group(2) - //kali//
						 * m.group(3) - //systemd-logind[316]:/login[6919]:/realmd[1197]://
						 * m.group(4) - //pam_unix(cron:session): session closed for user root//
						 */

						AgentDTO agentDTO = new AgentDTO();
						//agentDTO.setTimeLog(m.group(1));
						
						
						int b = RequestHandler.Send(agentDTO);
						
						if(b == -1) {
							//ukoliko ne moze da posalje na server mora 
							//sacuvati negde u lokalu dok ne bude mogao da salje
							System.out.println("Ne radi");
							//id = idFirst;
						} else {
							System.out.println("Sacuvano");
							//id = idFirst;
						}
					}
					

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
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
