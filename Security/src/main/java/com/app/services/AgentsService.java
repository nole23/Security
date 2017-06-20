package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.SearchDTO;
import com.app.model.Agents;
import com.app.repository.AgetnsRepository;

@Service
public class AgentsService {

	@Autowired
	private AgetnsRepository agentRepository;
	
	public List<Agents> search(SearchDTO dto) {
		List<Agents> agent = new ArrayList<Agents>();
		
		agent = agentRepository.search(dto.getMessages(), dto.getComputerName(), dto.getType(), dto.getDd(), dto.getSourceLog(), dto.getLogType(), dto.getRecordNumber());
	
		return agent;
	}
}
