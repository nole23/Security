package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
