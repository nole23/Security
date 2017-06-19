package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dto.FileLogDTO;
import com.app.dto.SearchLogsDTO;
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

	public List<FileLog> searchFor(SearchLogsDTO dto) {
		List<FileLog> logs = new ArrayList<FileLog>();

		// its not search by regular expression
		FileLogDTO fDto = dto.getFileDto();
		logs = repository.searchFor(fDto.getMessage(), fDto.getSource(), fDto.getTime(), fDto.getServerTime(),
				fDto.getDate(), fDto.getAgentId(), fDto.getCount(), fDto.getTagName(), fDto.getLogLevel(),
				fDto.getPlatform());
		// logs = repository.searchFor(fDto.getPlatform());

		return logs;
	}

}
