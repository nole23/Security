package com.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AlarmingDTO;
import com.app.dto.FileLogDTO;
import com.app.dto.ResponseMessageDTO;
import com.app.model.Agents;
import com.app.model.Alarming;
import com.app.model.FileLog;
import com.app.model.User;
import com.app.repository.AgetnsRepository;
import com.app.repository.AlarmingRespository;
import com.app.repository.UserRepository;
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

	/**
	 * Save log from client example: {
	 * 
	 * "message":"poriuka", "source":"162.12.111", "time":"11200",
	 * "serverTime":"12550", "agentId":"agentFIrst", "tagName":"someTag",
	 * "logLevel":"2", "platform":"windwos"
	 * 
	 * }
	 * 
	 * @see FileLogDTO
	 * @param fileLogDTO
	 * @return
	 */
	@RequestMapping(value = "/store", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessageDTO> storeLog(@RequestBody FileLogDTO fileLogDTO, HttpServletRequest request) {

		/*
		 * Uslucaju da nepo pokusa uhakovati svoj log provera da li je log
		 * validan i ukoli je validan proverava da li je taj logo vec prijavljen
		 */
		Agents agent = agentsRepository.findByAgentId(fileLogDTO.getAgentId());
		if (agent == null) {
			// dohvati IP adressu sa koje je dosao poziv
			String remoteAddr = "";

			if (request != null) {
				remoteAddr = request.getHeader("X-FORWARDED-FOR");
				if (remoteAddr == null || "".equals(remoteAddr)) {
					remoteAddr = request.getRemoteAddr();
				}
				Alarming alarming = new Alarming();
				alarming.setMessage("Agent with unknow IP adress, tried to log file");
				
				Agents ag = new Agents();
				//ag.setIpAddress(remoteAddr);
				//ag.setAgentId(fileLogDTO.getAgentId());
				//ag.setNameBot("Unknow agent");
				
				alarming.setAgents(ag);
				Date date = new Date();
				alarming.setDate(date);

				// snimi agenta prvo
				agentsRepository.save(ag);

				// save to repository
				alarmingRepository.save(alarming);

				return new ResponseEntity<ResponseMessageDTO>(new ResponseMessageDTO("Log archived"),
						HttpStatus.CREATED);
			}
		}
		FileLog fileLog = fileLogDTO.getFileLog();
		fileLogService.save(fileLog);

		return new ResponseEntity<ResponseMessageDTO>(new ResponseMessageDTO("Log archived"), HttpStatus.CREATED);
	}

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

}
