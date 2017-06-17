package com.app.controllers;

import java.security.Principal;

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
			
		System.out.println("|"+agentsDTO.getMessages());
		
		//System.out.println(agentsDTO.getComputerName());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
