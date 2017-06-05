package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FileLogDTO;
import com.app.dto.ResponseMessageDTO;
import com.app.model.FileLog;
import com.app.services.FileLogService;

@RestController
@RequestMapping(value = "/api/log")
public class FileLogController {

	@Autowired
	private FileLogService fileLogService;

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
	public ResponseEntity<ResponseMessageDTO> storeLog(@RequestBody FileLogDTO fileLogDTO) {

		FileLog fileLog = fileLogDTO.getFileLog();
		fileLogService.save(fileLog);

		return new ResponseEntity<ResponseMessageDTO>(HttpStatus.CREATED);
	}
}
