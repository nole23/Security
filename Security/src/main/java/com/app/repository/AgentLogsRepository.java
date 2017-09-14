package com.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.AgentLogs;
import com.app.model.PlatformType;

public interface AgentLogsRepository extends MongoRepository<AgentLogs, Long>{
	
	AgentLogs findByAgentEquals(Long agent);

	List<AgentLogs> findByAgent(Long agent);

	Page<AgentLogs> findAll(Pageable pageable);

	List<AgentLogs> findByPlatform(PlatformType platform);

	List<AgentLogs> findByType(String type);

	List<AgentLogs> findAllByAgentAndType(Long agent, String type);

	List<AgentLogs> findAllByAgentEqualsAndTimeBetween(Long agent, Date startData, Date endData);

	

}

