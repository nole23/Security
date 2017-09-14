package com.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LogDTO;
import com.app.dto.SearchDTO;
import com.app.model.AgentLogs;
import com.app.model.LevelLog;
import com.app.model.PlatformType;
import com.app.model.User;
import com.app.repository.AgentLogsRepository;
import com.app.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/log")
public class LogsController {

	@Autowired
	AgentLogsRepository agentRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * Pretraga po svim parametrima
	 */
	@RequestMapping(value="/all", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<LogDTO>> allLog(@RequestBody SearchDTO searchDTO) {
		Map<String, Object> model = new HashMap<>();

		User user = null;
		
		List<LogDTO> logDTO = new ArrayList<>();
		List<AgentLogs> log = null;
		
		
		if(!searchDTO.getRegular().equals("")){
			System.out.println("Po regularnom izrazu");
			
			Query query = new Query();
			
			Criteria criteria = new Criteria();
			criteria.orOperator(Criteria.where("errorLog.message").regex(searchDTO.getRegular()), 
					Criteria.where("type").regex(searchDTO.getRegular()), 
					Criteria.where("host").regex(searchDTO.getRegular()),
					Criteria.where("platform").regex(searchDTO.getRegular()),
					Criteria.where("errorLog.error").regex(searchDTO.getRegular()),
					Criteria.where("errorLog.logLevel").regex(searchDTO.getRegular()));
			
			query.addCriteria(criteria);
			log = mongoTemplate.find(query, AgentLogs.class);
			
		} else {
			System.out.println("Po nekim kriterijumima");
			
			if(!searchDTO.getAgentId().equals("")){
				System.out.println("agentove provere");
				if(!searchDTO.getType().equals("") && searchDTO.getStartData() == null){
					System.out.println("Provera po agentu i log levelu");
					
					System.out.println(searchDTO.getAgentId() + "|" + LevelLog.valueOf(searchDTO.getType()));
					
					log = agentRepository.findByAgentAndErrorLog_LogLevel(Long.parseLong(searchDTO.getAgentId()), LevelLog.valueOf(searchDTO.getType()));
				
				} else if(!searchDTO.getType().equals("") && searchDTO.getStartData() != null){
					System.out.println("Provera po agentu logu i datumu");

					log = agentRepository.findByAgentAndErrorLog_LogLevelAndTimeBetween(Long.parseLong(searchDTO.getAgentId()), LevelLog.valueOf(searchDTO.getType()), searchDTO.getStartData(), searchDTO.getEndData());
				
				} else if(searchDTO.getType().equals("") && searchDTO.getStartData() != null) {
					System.out.println("Provera po agentu i datumu");
					
					log = agentRepository.findByAgentAndTimeBetween(Long.parseLong(searchDTO.getAgentId()), searchDTO.getStartData(), searchDTO.getEndData());
					
				} else {
					System.out.println("Proveta samo po agentu");
					
					log = agentRepository.findByAgent(Long.parseLong(searchDTO.getAgentId()));
					
				}
			} else if(searchDTO.getAgentId().equals("") && !searchDTO.getType().equals("")){
				System.out.println("Pretraga po logu"); 
				
				if(!searchDTO.getPlatforma().equals("") && searchDTO.getStartData() == null){
					System.out.println("pretraga po loga i platforme");
					
					log = agentRepository.findByErrorLog_LogLevelAndPlatform(LevelLog.valueOf(searchDTO.getType()), PlatformType.valueOf(searchDTO.getPlatforma()));
					
				} else if(!searchDTO.getPlatforma().equals("") && searchDTO.getStartData() != null){
					System.out.println("pretraga po log level platformi i datumu");
					
					log = agentRepository.findByErrorLog_LogLevelAndPlatformAndTimeBetween(LevelLog.valueOf(searchDTO.getType()), PlatformType.valueOf(searchDTO.getPlatforma()), searchDTO.getStartData(), searchDTO.getEndData());
					
				} else {
					System.out.println("pretraga po loglevelu");
					
					log = agentRepository.findByErrorLog_LogLevel(LevelLog.valueOf(searchDTO.getType()));
					
				}
			} else if(!searchDTO.getPlatforma().equals("")){
				System.out.println("pretraga po platformi");
				if(!searchDTO.getPlatforma().equals("") && searchDTO.getStartData() != null){
					System.out.println("pretraga po platformi i datumu");
					
					log = agentRepository.findByPlatformAndTimeBetween(PlatformType.valueOf(searchDTO.getPlatforma()), searchDTO.getStartData(), searchDTO.getEndData());
					
				} else {
					System.out.println("preraga samo po platformi");
					
					log = agentRepository.findByPlatform(PlatformType.valueOf(searchDTO.getPlatforma()));
					
				}
			} else {
				System.out.println("Pretraga po datumu samo");
				
				log = agentRepository.findByTimeBetween(searchDTO.getStartData(), searchDTO.getEndData());
			}
		}
		
		
		for(AgentLogs l: log){
			LogDTO lDTO = new LogDTO();
			lDTO.setDate(l.getTime());
			lDTO.setError(l.getErrorLog().getError());
			lDTO.setHots(l.getHost());
			lDTO.setLevel(l.getErrorLog().getLogLevel());
			lDTO.setMessage(l.getErrorLog().getMessage());
			lDTO.setPlatform(l.getPlatform());
			lDTO.setType(l.getType());
			user = userRepository.findOne(l.getAgent());
			lDTO.setUsername(user.getUsername());
			
			logDTO.add(new LogDTO(lDTO));
		}


		return new ResponseEntity<>(logDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/all/enum", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> all() {
		Map<String, Object> model = new HashMap<>();
		List<String> list = new ArrayList<>();
		List<String> platform = new ArrayList<>();
		
		for(LevelLog l: LevelLog.values()){
			list.add(l.toString());
		}

		for(PlatformType p: PlatformType.values()){
			platform.add(p.toString());
		}
		
		model.put("levelLog", list);
		model.put("platform", platform);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
