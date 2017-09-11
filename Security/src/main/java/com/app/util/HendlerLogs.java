package com.app.util;

import java.io.FileWriter;
import java.util.Date;

import org.json.simple.JSONObject;

public class HendlerLogs {

	public static final String MESSAGE1 = "A login attempt from another location to the same user account";
	public static final String MESSAGE2 = "The user with given creditionals allready exists";
	public static final String MESSAGE3 = "Registraciju pokusao neko ko nije admin";
	
	@SuppressWarnings("unchecked")
	public static void saveLog(String type, String username, String typeMessage){
		
		
		String messages = null;
		if(type.equals("multipate_attempt"))
			messages = MESSAGE1;
		
		if(type.equals("duplicate_key"))
			messages = MESSAGE2;
		
		if(type.equals("not_admin"))
			messages = MESSAGE3;
		
		Date date = new Date();
		JSONObject obj = new JSONObject();
		obj.put("type", type);
		obj.put("messages", messages);
		obj.put("type_message", typeMessage);
		obj.put("ip", "123.456.789");
		obj.put("username", username);
		obj.put("time", date);
		
		try  {
			@SuppressWarnings("resource")
			FileWriter file = new FileWriter("hendlerLog/logs.bep", true);
			file.write("\n");
			file.write(obj.toJSONString());
			file.flush();
			
			
		} catch(Exception e) {
			System.out.println("Could not creat file");
		}
		
	}
}
