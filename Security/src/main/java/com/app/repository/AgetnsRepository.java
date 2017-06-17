package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Agents;

public interface AgetnsRepository extends JpaRepository<Agents, Long> {

	Agents findByRecordNumber(String recordNumber);

}
