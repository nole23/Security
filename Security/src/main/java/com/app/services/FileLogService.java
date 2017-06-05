package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.model.FileLog;
import com.app.repository.FileLogRepository;

@Service
public class FileLogService {

	@Autowired
	private FileLogRepository repository;

	public FileLog findOne(Long id) {
		return repository.findOne(id);
	}

	public List<FileLog> findAll() {
		return repository.findAll();
	}

	public Page<FileLog> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public FileLog save(FileLog fLog) {
		return repository.save(fLog);
	}

	public void remove(Long id) {
		repository.delete(id);
	}

}
