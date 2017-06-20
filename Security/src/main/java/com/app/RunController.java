package com.app;

import com.app.controllers.AgentController;

public class RunController {
	
	public static void Timer() {
		
		boolean start = true;
		while(start) {
			try {
				
				System.out.println("Uspavao sam ga");
				
				Thread.sleep(10000);
				
				//AgentController.getLogBySystem()
			} catch (Exception e) {
				e.printStackTrace();
				start = false;
			}
			
			
		}
	}

}
