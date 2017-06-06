package com.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping(value = "/registration")
	public ResponseEntity<String> newAgents(@RequestBody AgentDTO agentDTO, Principal principal) {
		

		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		//Chance user login and role
		User user = userRepository.findByUsername(principal.getName());
		if(user.getRole().getRole().getName().equals("admin"))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		
		//Chance agents name exists
		Agents nameB = agentsRepository.findByNameBot(agentDTO.getNameBot());
		if(nameB != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		//Chance agents ip address exists
		Agents ip = agentsRepository.findByIpAddress(agentDTO.getIpAddress());
		if(ip != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Agents agents = new Agents();
		
		agents.setNameBot(agentDTO.getNameBot());
		agents.setIpAddress(agentDTO.getIpAddress());

		agents.setPassword(agentDTO.getPassword());

		agents.setPassword(encoder.encode(agentDTO.getPassword()));

		
		agentsRepository.save(agents);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
