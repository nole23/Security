package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Agents;
import com.app.model.User;

public interface AgetnsRepository extends JpaRepository<Agents, Long> {

	Agents findByRecordNumber(String recordNumber);

	List<Agents> findByUser(User user);

	List<Agents> findByLogType(String type);

}
