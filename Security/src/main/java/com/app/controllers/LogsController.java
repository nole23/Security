package com.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.BasicQuery;
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

import com.app.dto.LogDTO;
import com.app.dto.LoginDTO;
import com.app.dto.SearchDTO;
import com.app.model.AgentLogs;
import com.app.model.ErrorLog;
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
	/**
	 * Pretraga po svim parametrima
	 */
	@RequestMapping(value="/all", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<LogDTO>> allLog(@RequestBody SearchDTO searchDTO) {
		Map<String, Object> model = new HashMap<>();

		User user = null;
		
		List<LogDTO> logDTO = new ArrayList<>();
		
		/**
		 * Ovo radi i vraca samo za jednog agenta sve logove
		 */
		List<AgentLogs> log = null;
		if(searchDTO.getVrst().equals("agent")){
			user = userRepository.findOne(Long.parseLong(searchDTO.getAgentId()));
			log = agentRepository.findByAgent(Long.parseLong(searchDTO.getAgentId()));
			System.out.println("1");
		}
		
		if(searchDTO.getVrst().equals("tip")){
			log = agentRepository.findByType(searchDTO.getType());
			System.out.println("2");
		}
		
		if(searchDTO.getVrst().equals("agent_tip")){
			log = agentRepository.findAllByAgentAndType(Long.parseLong(searchDTO.getAgentId()), searchDTO.getType());
			System.out.println("3");
		}
		
		if(searchDTO.getVrst().equals("vreme_agent")){
			log = agentRepository.findAllByAgentEqualsAndTimeBetween(Long.parseLong(searchDTO.getAgentId()), searchDTO.getStartData(), searchDTO.getEndData());
			System.out.println("4");
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
}
