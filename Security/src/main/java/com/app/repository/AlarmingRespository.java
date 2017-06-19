package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Alarming;

public interface AlarmingRespository extends JpaRepository<Alarming, Long> {

	List<Alarming> findByTypeLog(String typeLog);

	List<Alarming> findByCountTime(int countTime);

}
