package com.app.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.model.AgentLogs;
import com.app.repository.AgentLogsRepository;

@RestController
@RequestMapping(value = "/api/log")
public class LogsController {

	@Autowired
	AgentLogsRepository agentRepository;
	
	/**
	 * Pretraga po svim parametrima
	 */
	@RequestMapping(value="/all/{agentId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> allLog(Principal principal, @PathVariable String agentId) {
		Map<String, Object> model = new HashMap<>();
		
		
		
		
		if(!agentId.equals("null")){
			Long id = Long.parseLong(agentId);
			AgentLogs logs = agentRepository.findByAgent(id);
			System.out.println("1");
			model.put("list", logs);
		} else {
			List<AgentLogs> logs = agentRepository.findAll();
			System.out.println("2");
			model.put("list", logs);
		}
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
