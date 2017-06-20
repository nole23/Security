package com.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.FileLog;

public interface FileLogRepository extends JpaRepository<FileLog, Long> {

	/*
	 * @Query("SELECT f from FileLog as f WHERE f.platform = :platform") public
	 * List<FileLog> searchFor( @Param("platform") String platform);
	 */
	@Query("SELECT f from FileLog as f WHERE (f.message LIKE %:message%) OR (f.source LIKE %:source%)  OR (f.time =  :time) OR (f.serverTime = :serverTime) OR (f.date =  :date) OR (f.agentId LIKE %:agentId%) OR (f.count = :count) OR (f.tagName LIKE %:tagName%) OR (f.logLevel = :logLevel) OR (f.platform LIKE %:platform%)")
	public List<FileLog> searchFor(@Param("message") String message, @Param("source") String source,
			@Param("time") int time, @Param("serverTime") int serverTime, @Param("date") Date date,
			@Param("agentId") String agentId, @Param("count") int count, @Param("tagName") String tagName,
			@Param("logLevel") int logLevel, @Param("platform") String platform);
	
	
	

}
