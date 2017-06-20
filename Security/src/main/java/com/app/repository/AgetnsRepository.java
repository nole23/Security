package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Agents;
import com.app.model.User;

public interface AgetnsRepository extends JpaRepository<Agents, Long> {

	Agents findByRecordNumber(String recordNumber);

	List<Agents> findByUser(User user);

	List<Agents> findByLogType(String type);
	
	
	@Query("SELECT f from Agents as f WHERE (f.messages LIKE %:messages%) OR (f.computerName LIKE %:computerName%)  OR (f.type LIKE %:type%) OR (f.dd = :dd) OR (f.sourceLog LIKE %:sourceLog%) OR (f.logType LIKE %:logType%) OR (f.recordNumber LIKE %:recordNumber%) ")
	public List<Agents> search(@Param("messages") String message, @Param("computerName") String computerName, @Param("type") String type, @Param("dd") int dd, @Param("sourceLog") String sourceLog, @Param("logType") String logType, @Param("recordNumber") String recordNumber);

}
