package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.AgentLogs;

public interface AgentLogsRepository extends MongoRepository<AgentLogs, Long>{

	AgentLogs findByAgent(Long agent);

	

}

