package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.app.model.AgentLogs;
import com.app.repository.AgentLogsRepository;

@Service
public class AgentLogService {

	private final AgentLogsRepository agentRepository;
	//private final MongoOperations operations;
	
	@Autowired
	public AgentLogService(final AgentLogsRepository agentLogsRepository, final MongoOperations operations){
		this.agentRepository = agentLogsRepository;
		//this.operations = operations;
	}
	
	public AgentLogs save(final AgentLogs agentLogs){
		return agentRepository.save(agentLogs);
	}

	public AgentLogs findByAgent(Long id) {
		AgentLogs agent = agentRepository.findByAgentEquals(id);
		return agent;
	}
}
