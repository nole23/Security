package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

}
