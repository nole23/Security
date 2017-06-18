package com.app.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AgentDTO;
import com.app.dto.LoginDTO;
import com.app.dto.MesagesDTO;
import com.app.dto.UserDTO;
import com.app.model.Agents;
import com.app.model.User;
import com.app.repository.AgetnsRepository;
import com.app.repository.UserRepository;
import com.app.security.TokenUtils;

@RestController
@RequestMapping(value = "/api/agent")
public class AgentController {

	@Autowired
	private AgetnsRepository agentsRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> loginAgent(@RequestBody LoginDTO loginDTO) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			
			return new ResponseEntity<>(tokenUtils.generateToken(details), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveLogFileWindow(@RequestBody AgentDTO agentsDTO, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());

		if (!user.getRole().getRole().getName().equals("AGENT")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Agents ag = agentsRepository.findByRecordNumber(agentsDTO.getRecordNumber());

		if (ag == null) {

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
			if (!ag.getTimeLog().equals(agentsDTO.getTimeLog())) {
				System.out.println("okinuti prvi alarm");
			}

		}

		// Proverava da li je ponoc ako je ponoc
		// poziva se metoda za ciscenja baze i upisivanje svih elemenata u
		// MongoDB
		// Ovaj deo treba na drugo mesto prebaciti

		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String datestring = dateFormat.format(date);

		String min[] = datestring.split(":");
		int sati = Integer.parseInt(min[0]);
		int minut = Integer.parseInt(min[1]);

		if (sati == 0) {
			if (minut <= 10) {
				// ovde ide opet nesto
			}
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Prikaz svih agenata
	 * @param agentId
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllAgent() {

		List<User> user = userRepository.findAll();
		
		List<UserDTO> userDTO = new ArrayList<>();
		for(User u: user) {
			if(u.getRole().getRole().getName().equals("AGENT"))
				userDTO.add(new UserDTO(u));
		}

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	
	/**
	 * Ispis svih logova jednog agenta
	 * @param agentId
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/all/{agentId}",method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> getLogByAgent(@PathVariable Long agentId, Principal principal) {

		User user = userRepository.findOne(agentId);
		
		List<Agents> agent = agentsRepository.findByUser(user);
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			agentDTO.add(new AgentDTO(a));
		}

		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
	


	@RequestMapping(value="/all/sat/{agentId}",method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> getLogByAgentSat(@PathVariable Long agentId, Principal principal) {

		User user = userRepository.findOne(agentId);
		
		
		//Ne radi jos uvek 
		List<Agents> agent = agentsRepository.findByUser(user);
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			agentDTO.add(new AgentDTO(a));
		}

		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
	
	/**
	 * Ispis svih logova po tipu
	 */
	@RequestMapping(value="/all//type/{type}",method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> getLogByAgent(@PathVariable String type, Principal principal) {

		List<Agents> agent = agentsRepository.findByLogType(type);
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			agentDTO.add(new AgentDTO(a));
		}

		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
}
