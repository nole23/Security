package com.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.Alarming;
import com.app.repository.AlarmingRespository;

@Controller
@RequestMapping(value = "/api/alarm")
public class AlarmController {

	@Autowired
	private AlarmingRespository alarmingRepository;
	
	
	/**
	 * Ispis okidaca alarma, ovo je namenjeno za sve logove
	 * @return
	 */
	@RequestMapping(value="/all/{type}",method = RequestMethod.GET)
	public ResponseEntity<Alarming> getAllAlarm(@PathVariable String type) {

		Alarming alarm = alarmingRepository.findByTypeLog(type);
		
		return new ResponseEntity<>(alarm, HttpStatus.OK);
	}
}
