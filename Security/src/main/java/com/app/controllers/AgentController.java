package com.app.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AgentDTO;
import com.app.model.Agents;
import com.app.model.User;
import com.app.repository.AgetnsRepository;
import com.app.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/agent")
public class AgentController {
	
	@Autowired
	private AgetnsRepository agentsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveLogFileWindow(@RequestBody AgentDTO agentsDTO, Principal principal) {
		
		User user = userRepository.findByUsername(principal.getName());
		
		if(!user.getRole().getRole().getName().equals("AGENT")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		Agents ag = agentsRepository.findByRecordNumber(agentsDTO.getRecordNumber());
		
		if(ag == null) {
			
			
			Agents agents = new Agents();
			agents.setUser(user);
			agents.setRecordNumber(agentsDTO.getRecordNumber());
			agents.setLogType(agentsDTO.getLogType());
			agents.setTimeLog(agentsDTO.getTimeLog());
			agents.setSourceLog(agentsDTO.getSourceLog());
			agents.setComputerName(agentsDTO.getComputerName());
			agents.setMessages(agentsDTO.getMessages());
			agents.setType(agentsDTO.getType());
			
			agentsRepository.save(agents);
			
			
			
		} else {
			System.out.println("Sacuvano vec u bazi!: " + ag.getRecordNumber());
			int count = 0;
			if(!ag.getTimeLog().equals(agentsDTO.getTimeLog())){
				System.out.println("okinuti prvi alarm");
			}
			
			
		}

		//Proverava da li je ponoc ako je ponoc
		//poziva se metoda za ciscenja baze i upisivanje svih elemenata u 
		//MongoDB
		//Ovaj deo treba na drugo mesto prebaciti 
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String datestring = dateFormat.format(date);
		
		String min[] = datestring.split(":");
		int sati = Integer.parseInt(min[0]);
		int minut = Integer.parseInt(min[1]);
		
		if(sati == 0) {
			if(minut <= 10) {
				//ovde ide opet nesto
			}
		}
			
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
