package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Premission;

public interface PermissionRepository extends JpaRepository<Premission, Long> {

}
