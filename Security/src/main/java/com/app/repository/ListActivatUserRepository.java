package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.ListActivateUser;
import com.app.model.User;

public interface ListActivatUserRepository extends JpaRepository<ListActivateUser, Long> {

	ListActivateUser findByUser(User user);

}
