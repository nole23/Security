package com.app.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.AlarmDef;
import com.app.model.User;
import com.app.repository.AlarmDefRepository;
import com.app.repository.AlarmRepository;
import com.app.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/alarm")
public class AlarmController {

	@Autowired
	private AlarmRepository alarmRepository;

	@Autowired
	private AlarmDefRepository alarmDefRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/define", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> defineAlarm(@RequestBody AlarmDef alarmDef, Principal principal, CsrfToken token) {

		User user = userRepository.findByUsername(principal.getName());
		AlarmDef result = alarmDefRepository.save(alarmDef);

		Map<String, Object> model = new HashMap<>();
		model.put("response", "Sucessfully created alarm rules");

		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}

}
