package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.UserInformacion;

public interface UserInformacionRepository extends JpaRepository<UserInformacion, Long> {

}
