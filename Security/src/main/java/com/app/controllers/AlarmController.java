package com.app.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dto.AlarmingDTO;
import com.app.dto.MesagesDTO;
import com.app.dto.UserDTO;
import com.app.model.Alarming;
import com.app.repository.AlarmingRespository;

@Controller
@RequestMapping(value = "/api/alarm")
public class AlarmController {

	@Autowired
	private AlarmingRespository alarmingRepository;
	
	

	
	/**
	 * Vratu listu sa jednim elementom koja nam kaze koliko puta se nesto ponavlja 
	 * za trazeni tip
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/all/{type}",method = RequestMethod.GET)
	public ResponseEntity<List<AlarmingDTO>> getAllAlarm1(@PathVariable String type) {

		
		List<Alarming> alarm = alarmingRepository.findByTypeLog(type);
		
		List<AlarmingDTO> alarmingDTO = new ArrayList<>();
		for(Alarming al: alarm) {
			alarmingDTO.add(new AlarmingDTO(al));
		}
		
		return new ResponseEntity<>(alarmingDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/new",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<MesagesDTO> addAlar(@RequestBody AlarmingDTO alarmDTO) {

		MesagesDTO message = new MesagesDTO();
		Alarming alarm = new Alarming();
		
		alarm.setCountLog(alarmDTO.getCountLog());
		alarm.setCountTime(alarmDTO.getCountTime());
		alarm.setPrioritet(alarmDTO.getPrioritet());
		alarm.setTypeLog(alarmDTO.getTypeLog());
		
		alarmingRepository.save(alarm);
		
		message.setMessage("success");
		return new ResponseEntity<MesagesDTO>(message, HttpStatus.OK);
	}
}
