package agent;

import org.json.simple.JSONObject;

public class Monitor implements Runnable {

	protected String id;
	
	Monitor(JSONObject config) {
		id = (String) config.get("id"); 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
