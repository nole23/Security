package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
