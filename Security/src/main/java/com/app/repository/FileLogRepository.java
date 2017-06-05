package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.FileLog;

public interface FileLogRepository extends JpaRepository<FileLog, Long> {

}
