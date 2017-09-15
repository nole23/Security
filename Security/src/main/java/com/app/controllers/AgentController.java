package com.app.controllers;


import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.app.dto.UserDTO;
import com.app.model.AgentLogs;
import com.app.model.LevelLog;
import com.app.model.ListActivateUser;
import com.app.model.StausLogin;
import com.app.model.User;
import com.app.repository.AgentLogsRepository;
import com.app.repository.ListActivatUserRepository;
import com.app.repository.UserRepository;
import com.app.security.TokenUtils;
import com.app.services.AgentLogService;
import com.app.util.Ruls;


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
	
	@Autowired
	ListActivatUserRepository listActivatUserRepository;

	@Autowired
	UserRepository userResponse;
	
	@Autowired
	Ruls ruls;

	@Autowired
	AgentLogsRepository agentLogsRepository;
	
	/**
	 * Logovanje agenata
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> loginKorisnik(@RequestBody LoginDTO loginDTO,CsrfToken token1) {
		Map<String, Object> model = new HashMap<>();
		try{
			User user = userResponse.findByUsername(loginDTO.getUsername());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

			//System.out.println(token1.getToken() + " ! " + token1.getHeaderName());
	        //
			
			Date date = new Date();
			
			ListActivateUser listActivateUser = new ListActivateUser();
			listActivateUser.setPrivateKey(token1.getToken());
			listActivateUser.setDateLogin(date);
			listActivateUser.setUser(user);
			listActivateUser.setStatusLogin(StausLogin.ACTIVE);
			listActivatUserRepository.save(listActivateUser);
			
			
			model.put("csrf", token1.getToken());
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/logs/{csrf}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> registration(@RequestBody AgentLogs agentLogs, Principal principal, CsrfToken token1, @PathVariable String csrf) throws ParseException {
		
		Map<String, Object> model = new HashMap<>();
		User user = userRepository.findByUsername(principal.getName());
		ListActivateUser list = listActivatUserRepository.findByUser(user);
		//System.out.println("1");
		//System.out.println(agentLogs.getErrorLog().getLogLevel());
		//System.out.println("2");
//		if(list.getPrivateKey().equals(csrf)){
//			model.put("error", true);
//			System.out.println("3");
//			return new ResponseEntity<>(model, HttpStatus.OK);
//		}
		//System.out.println("4");
		//System.out.println(agentLogs.getTime().getTime()-7199978 + " | " +new Date().getTime());
		AgentLogs agentLogs1 = new AgentLogs();
		agentLogs1 = agentLogs;
		agentService.save(agentLogs1);
		//System.out.println("5");
		
		//TODO Kad sve ovo prodje poslati log na proveru
		
		
		
		//TODO Sacuvati novi token radi provere da neko nije uhakovao nesto
		list.setPrivateKey(token1.getToken());
		listActivatUserRepository.save(list);
		
		//Provera pravila
		
		ruls.check(user);
		//System.out.println(nn);
		model.put("csrf", token1);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	


	@RequestMapping(value = "/all/agent", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> allLog() {
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		List<User> user = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();
		
		for(User u: user){
			if(u.getUserRole().getRole().getName().equals("AGENT")){
				userDTO.add(new UserDTO(u));
			}
		}
		
		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}
	
	
	private void chech(User user) {
		// TODO Auto-generated method stub
		System.out.println("Upao " + user.getUsername());
	}
}


