package com.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.AgentLogs;
import com.app.model.LevelLog;
import com.app.model.PlatformType;

public interface AgentLogsRepository extends MongoRepository<AgentLogs, Long>{
	
	AgentLogs findByAgentEquals(Long agent);

	List<AgentLogs> findByAgentAndErrorLog_LogLevel(Long agentId, LevelLog levelLog);
	
	List<AgentLogs> findByAgentAndErrorLog_LogLevelAndTimeBetween(Long agentId, LevelLog levelLog, Date startTime, Date endTime);

	List<AgentLogs> findByAgentAndTimeBetween(Long agentId, Date startTime, Date endTime);

	List<AgentLogs> findByAgent(Long agentId);
	
	List<AgentLogs> findByErrorLog_LogLevelAndPlatform(LevelLog levelLog, PlatformType platform);

	List<AgentLogs> findByErrorLog_LogLevelAndPlatformAndTimeBetween(LevelLog levelLog, PlatformType platform, Date startTime, Date endTime);

	List<AgentLogs> findByErrorLog_LogLevel(LevelLog levelLog);

	List<AgentLogs> findByPlatformAndTimeBetween(PlatformType platform, Date startTime, Date endTime);

	List<AgentLogs> findByPlatform(PlatformType platform);

	List<AgentLogs> findByTimeBetween(Date startTime, Date endTime);
	

}

