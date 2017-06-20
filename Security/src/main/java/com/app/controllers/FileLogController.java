package com.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.Spring;

import org.codehaus.plexus.util.dag.DAG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AgentDTO;
import com.app.dto.AlarmingDTO;
import com.app.dto.FileLogDTO;
import com.app.dto.SearchDTO;
import com.app.dto.SearchLogsDTO;
import com.app.model.Agents;
import com.app.model.Alarming;
import com.app.model.FileLog;
import com.app.model.User;
import com.app.repository.AgetnsRepository;
import com.app.repository.AlarmingRespository;
import com.app.repository.UserRepository;
import com.app.services.AgentsService;
import com.app.services.FileLogService;

@RestController
@RequestMapping(value = "/api/log")
public class FileLogController {

	@Autowired
	private FileLogService fileLogService;

	@Autowired
	private AlarmingRespository alarmingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AgetnsRepository agentsRepository;
	
	@Autowired
	private AgentsService agentsService;

	/**
	 * View all logs
	 * 
	 * @return
	 */
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<List<FileLogDTO>> listLog(Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		if (user == null)
			return new ResponseEntity<List<FileLogDTO>>(HttpStatus.BAD_REQUEST);

		List<FileLog> fileLog = fileLogService.findAll();

		List<FileLogDTO> fileLogDTO = new ArrayList<>();
		for (FileLog f : fileLog) {
			fileLogDTO.add(new FileLogDTO(f));
		}

		return new ResponseEntity<List<FileLogDTO>>(fileLogDTO, HttpStatus.OK);
	}

	/**
	 * View all alarm
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public ResponseEntity<List<AlarmingDTO>> listAlarm(Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		if (user == null)
			return new ResponseEntity<List<AlarmingDTO>>(HttpStatus.BAD_REQUEST);

		List<Alarming> alarming = alarmingRepository.findAll();

		List<AlarmingDTO> alarmingDTO = new ArrayList<>();
		for (Alarming a : alarming) {
			alarmingDTO.add(new AlarmingDTO(a));
		}

		return new ResponseEntity<List<AlarmingDTO>>(alarmingDTO, HttpStatus.OK);
	}

	/***
	 * Search the log files
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<FileLogDTO>> search(@RequestBody SearchLogsDTO dto ,Principal principal) {
		
		List<FileLogDTO> logs = new ArrayList<FileLogDTO>();
		
		List<FileLog> ll = fileLogService.searchFor(dto);

		for (FileLog l : ll) {

			logs.add(new FileLogDTO(l));

		}
		System.out.println(dto.isRegular());
		
		return new ResponseEntity<List<FileLogDTO>>(logs, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/my/search/{parameters}", method = RequestMethod.POST)
	public ResponseEntity<List<AgentDTO>> searchNesto(HttpServletRequest request, @PathVariable String parameters, ModelMap model)throws Exception {

		
		
		SearchDTO dto = new SearchDTO();
		dto.setComputerName(parameters);
		dto.setLogType(parameters);
		dto.setMessages(parameters);
		dto.setRecordNumber(parameters);
		dto.setSourceLog(parameters);
		dto.setType(parameters);
		
		
		List<Agents> ll = agentsService.search(dto);
		List<AgentDTO> agentDTO = new ArrayList<>();

		for (Agents l : ll) {

			agentDTO.add(new AgentDTO(l));

		}
		
		System.out.println(agentDTO);
		
		return new ResponseEntity<List<AgentDTO>>(agentDTO, HttpStatus.OK);
	}
}
