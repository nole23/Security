package com.app.controllers;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.model.AgentLogs;
import com.app.model.ErrorLog;
import com.app.model.User;
import com.app.repository.AgentLogsRepository;
import com.app.repository.UserRepository;
import com.app.security.TokenUtils;
import com.app.services.AgentLogService;


@RestController
@RequestMapping(value = "/api/agent")
public class AgentController {
	
	@Autowired
	AgentLogsRepository agentRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	AgentLogService agentService;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Logovanje agenata
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> loginKorisnik(@RequestBody LoginDTO loginDTO,CsrfToken token1) {
		Map<String, Object> model = new HashMap<>();
		try{
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

			//System.out.println(token1.getToken() + " ! " + token1.getHeaderName());
	        //
			model.put("csrf", token1);
	        model.put("username", loginDTO.getUsername());
	        model.put("jwt", tokenUtils.generateToken(details));
	        model.put("role", "agent");
			return new ResponseEntity<>(model, HttpStatus.OK);
		} catch(Exception ex) {
			model.put("error", "nesto ne valja " + ex);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
	}

	/**
	 * cuvanje pristiglih logova i provera da je aget
	 */
	@RequestMapping(value = "/logs/{csrf}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> registration(@RequestBody AgentLogs agentLogs, 
			final HttpEntity<String> httpEntity, Principal principal, CsrfToken token1, @PathVariable String csrf) {
		
		Map<String, Object> model = new HashMap<>();
		User user = userRepository.findByUsername(principal.getName());
		
		System.out.println(user.getId());
		
		if(user.getUserProfile() != null){
			model.put("error", true);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}

		
		AgentLogs agent = agentService.findByAgent(user.getId());
		
		if(agent != null){
			
			System.out.println("Agent postoji samo se dodaje element");
			
			ErrorLog error = new ErrorLog();
			error.setLogLevel(agentLogs.getInfo().getError().get(0).getLogLevel());
			error.setError(agentLogs.getInfo().getError().get(0).getError());
			error.setTime(agentLogs.getInfo().getError().get(0).getTime());
			error.setType(agentLogs.getInfo().getError().get(0).getType());
			
			agent.getInfo().getError().add(error);
			agentRepository.save(agent);
			System.out.println("sacuvali smo novi element errorLoga");
					
				
		} else {
			System.out.println("agent ne postoji1");
			AgentLogs agentLogs1 = new AgentLogs();
			agentLogs1 = agentLogs;
			
			agentService.save(agentLogs1);
		}
		
		//TODO Kad sve ovo prodje poslati log na proveru
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/logs", method = RequestMethod.GET)
	public ResponseEntity<String> allLog() {
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		System.out.println("Nesto radi");
		
		return new ResponseEntity<>("Sacuvano",HttpStatus.OK);
	}
}


