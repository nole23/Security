package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Alarming;

public interface AlarmingRespository extends JpaRepository<Alarming, Long> {

}
