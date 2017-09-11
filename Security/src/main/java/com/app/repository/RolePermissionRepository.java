package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.RolePremission;

public interface RolePermissionRepository extends JpaRepository<RolePremission, Long> {

	List<RolePremission> findById(Long id);

}
