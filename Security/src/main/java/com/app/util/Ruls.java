package com.app.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.SearchDTO;
import com.app.model.AgentLogs;
import com.app.model.Alarm;
import com.app.model.AlarmDef;
import com.app.model.LevelLog;
import com.app.model.User;
import com.app.repository.AgentLogsRepository;
import com.app.repository.AlarmDefRepository;
import com.app.repository.AlarmRepository;
import com.app.repository.UserRepository;

@Service
public class Ruls {


	@Autowired
	UserRepository userResponse;
	
	@Autowired
	AlarmRepository alarmRepository;
	
	@Autowired
	AlarmDefRepository alarmDefinite;
	
	@Autowired
	AgentLogsRepository agentLogsRepository;
	
	@SuppressWarnings("deprecation")
	public void check(User user) {
		// TODO Auto-generated method stub
//		System.out.println("Upao");
//		System.out.println(user.getId());
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setAgentId(user.getId().toString());
		searchDTO.setStartData(new Date(System.currentTimeMillis() - (750 * 1000)));
		searchDTO.setEndData(new Date());
		
		List<AlarmDef> alarmDef = alarmDefinite.findAll();
		//System.out.println(alarmDef);
		
		
		for(LevelLog l: LevelLog.values()){
			
			Date date = new Date();
			
			//System.out.println("1 "+l);
			
			for(AlarmDef ad: alarmDef){
				
				
				searchDTO.setType(l.toString());
				
				//System.out.println("2 " + ad.getTimeAlarm());
				
				List<AgentLogs> agentLogs = agentLogsRepository.findByAgentAndErrorLog_LogLevel(Long.parseLong(searchDTO.getAgentId()), LevelLog.valueOf(searchDTO.getType()));
				
				//System.out.println(agentLogs);
				int count = 0;
				for(AgentLogs al: agentLogs){

					if(al.getTime().getYear() == date.getYear()){
						if(al.getTime().getMonth() == date.getMonth()){
							if(al.getTime().getDay() == date.getDay()){
								if(al.getTime().getHours()-2 == date.getHours()){
									if(al.getTime().getMinutes() == date.getMinutes()){
										System.out.println(al.getTime().getSeconds() + " | " +date.getSeconds());
										if(al.getTime().getSeconds() > date.getSeconds() - ad.getTimeAlarm()){
											count += 1;
											System.out.println(count);
										}
									}
								}
							}
						}
					}
					
					//System.out.println(count);
					if(count >= ad.getMinCount() && count != 0 && count < ad.getMaxCount()){
						
							System.out.println("pravilo okinuto");
							Alarm alarm = new Alarm();
							alarm.setTime(new Date());
							alarm.setCount(count);
							alarm.setUser(user);
							alarm.setAlarmDef(ad);
							alarmRepository.save(alarm);
						
						
					}
				}
				
			}
		}
		
		
			
		
		
	}

	
}
