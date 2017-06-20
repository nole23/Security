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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AgentDTO;
import com.app.dto.AlarmReqvestDTO;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.model.Agents;
import com.app.model.Alarming;
import com.app.model.User;
import com.app.repository.AgetnsRepository;
import com.app.repository.AlarmingRespository;
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
	
	@Autowired
	private AlarmingRespository alarmingRespository;
	
	@Autowired
	AlarmController alarmController;
	
	
	/**
	 * Radi
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAgent(Principal principal) {

		List<User> user = userRepository.findAll();
		
		List<UserDTO> userDTO = new ArrayList<>();
		for(User u: user) {
			if(u.getUser_role().getRole().getName().equals("AGENT"))
				userDTO.add(new UserDTO(u));
		}

		
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Radi
	 * @param loginDTO
	 * @return
	 */
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

		if (user.getUser_role().getRole().getName().equals("ADMIN")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Agents ag = agentsRepository.findByRecordNumber(agentsDTO.getRecordNumber());

		if (ag == null) {

			Agents agents = new Agents();
			agents.setUser(user);
			agents.setRecordNumber(agentsDTO.getRecordNumber());
			agents.setLogType(agentsDTO.getLogType());
			agents.setYyyy(agentsDTO.getYyyy());
			agents.setMm(agentsDTO.getMm());
			agents.setDd(agentsDTO.getDd());
			agents.setHh(agentsDTO.getHh());
			agents.setMin(agentsDTO.getMin());
			agents.setSs(agentsDTO.getSs());
			agents.setSourceLog(agentsDTO.getSourceLog());
			agents.setComputerName(agentsDTO.getComputerName());
			agents.setMessages(agentsDTO.getMessages());
			agents.setType(agentsDTO.getType());

			agentsRepository.save(agents);

		} else {
			System.out.println("Sacuvano vec u bazi!: " + ag.getRecordNumber());
			
			

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
	 * Dnveni logovi
	 * radi
	 * @param agentId
	 * @param principal
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/all/day/{agentId}",method = RequestMethod.GET)
	public ResponseEntity<List<AgentDTO>> getLogByAgentDay(@PathVariable Long agentId, Principal principal) {

		User user = userRepository.findOne(agentId);
		
		Date date = new Date();
		int god = date.getYear()+1900;
		int mes = date.getMonth();
		int dan = date.getDate();

		List<Agents> agent = agentsRepository.findByUser(user);
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			if(god == a.getYyyy())
				if(mes == a.getMm())
					if(dan == a.getDd())
						agentDTO.add(new AgentDTO(a));
		}
		
		
		
		
		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
	
	/**
	 * Kreiranje alarma po tipu
	 * radi
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/all/type/{type}/{id}",method = RequestMethod.GET)
	public ResponseEntity<AlarmReqvestDTO> getLogByAgent(@PathVariable String type,@PathVariable Long id, Principal principal) {

		User user = userRepository.findOne(id);
		List<Agents> agent = agentsRepository.findByUser(user);
		Date date = new Date();
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			if(a.getDd() == date.getDate())
				if(a.getLogType().equals(type))
					agentDTO.add(new AgentDTO(a));
		}
		System.out.println(agentDTO);
		AlarmReqvestDTO alarmReqvesteDTO = new AlarmReqvestDTO();
		List<Alarming> alarm = alarmingRespository.findByTypeLog(type);
		for(Alarming al: alarm) {
			if(agentDTO.size() >= al.getCountLog()) {
				int vreme = (al.getCountTime()/60);
				if(date.getMinutes()-vreme < date.getMinutes()) {
					alarmReqvesteDTO.setIdAgenta(id);
					alarmReqvesteDTO.setIdAlarma(al.getId());
					alarmReqvesteDTO.setAgentSize(agentDTO.size());
					alarmReqvesteDTO.setType(al.getTypeLog());
					alarmController.saveAlarm(alarmReqvesteDTO);
					break;
				}
			}
		}
		
		
		return new ResponseEntity<>(alarmReqvesteDTO, HttpStatus.OK);
	}
	
	

	/**
	 * Alarm koji u toku dana ispise sve sistemske logove i proveri da li su dobri
	 * Radi
	 * @param id		//Id agenta kog proveravamo
	 * @param source 	//tip loga koji obradjujemo na primer System or Application logivi
	 * @param time  	//svi logovi koji su manji od jednog dana izrazeni u sekundama
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="day/log/{id}/{source}/{time}",method = RequestMethod.GET)
	public ResponseEntity<AlarmReqvestDTO> getLogBySystem(@PathVariable Long id, @PathVariable String source, @PathVariable int time) {

		System.out.println("Dosao ovde");
		User user = userRepository.findOne(id);
		List<Agents> agent = agentsRepository.findByUser(user);
		
		List<Alarming> alarm = alarmingRespository.findBySourceLog(source);
		
		Date date = new Date();
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			if(a.getSourceLog().equals(source))
				if(a.getYyyy() == date.getYear()+1900)
					if(a.getDd() == date.getDate())
						agentDTO.add(new AgentDTO(a));
		}

		
		AlarmReqvestDTO alarmReqvesteDTO = new AlarmReqvestDTO();
		for(Alarming al: alarm) {
			if(al.getCountTime() >= time){
				if(agentDTO.size() >= al.getCountLog()) {
					System.out.println("upao u zamku " +agentDTO.size());
					alarmReqvesteDTO.setIdAgenta(id);
					alarmReqvesteDTO.setIdAlarma(al.getId());
					alarmReqvesteDTO.setAgentSize(agentDTO.size());
					alarmReqvesteDTO.setType(al.getTypeLog());
					alarmController.saveAlarm(alarmReqvesteDTO);
					break;
				}
			}
				
		}
		

		return new ResponseEntity<>(alarmReqvesteDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Okida se alarm ukoliko se u na primer 10 sekundi desi odredjen broj ERROR logova
	 * radi
	 * @param id
	 * @param source
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="sec/log/{id}/{source}/{time}/{logType}",method = RequestMethod.GET)
	public ResponseEntity<AlarmReqvestDTO> getLogByERROR(@PathVariable Long id, @PathVariable String source, @PathVariable String logType,@PathVariable int time) {

		User user = userRepository.findOne(id);
		List<Agents> agent = agentsRepository.findByUser(user);
		
		List<Alarming> alarm = alarmingRespository.findBySourceLog(source);
		
		Date date = new Date();
		
		List<AgentDTO> agentDTO = new ArrayList<>();
		for(Agents a: agent) {
			if(a.getLogType().equals(logType))
				if(a.getDd() == date.getDate())
					if(a.getHh() == date.getHours())
						if(a.getMin() == date.getMinutes())
							if(a.getSs() <= date.getSeconds())
								agentDTO.add(new AgentDTO(a));
		}
		
		AlarmReqvestDTO alarmReqvesteDTO = new AlarmReqvestDTO();
		for(Alarming al: alarm) {
			if(al.getSourceLog().equals(source))
				if(al.getTypeLog().equals(logType)){
					if(agentDTO.size() > al.getCountLog()){
						alarmReqvesteDTO.setIdAgenta(id);
						alarmReqvesteDTO.setIdAlarma(al.getId());
						alarmReqvesteDTO.setAgentSize(agentDTO.size());
						alarmReqvesteDTO.setType(al.getTypeLog());
						alarmReqvesteDTO.setCountTime(al.getCountTime());
						alarmController.saveAlarm(alarmReqvesteDTO);
						break;
					}
				}
							
		}
		
		
		
		return new ResponseEntity<>(alarmReqvesteDTO, HttpStatus.OK);
	}
	
	
	
	
}
