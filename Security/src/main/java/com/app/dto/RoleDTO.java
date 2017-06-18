package com.app.dto;

import com.app.model.Role;

public class RoleDTO {

	private Long id;
	private String name;
	private User_RoleDTO user_roleDTO;

	public RoleDTO() {
	}

	public RoleDTO(Long id, String name, User_RoleDTO user_roleDTO) {
		super();
		this.id = id;
		this.name = name;
		this.user_roleDTO = user_roleDTO;
	}

	public RoleDTO(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User_RoleDTO getUser_roleDTO() {
		return user_roleDTO;
	}

	public void setUser_roleDTO(User_RoleDTO user_roleDTO) {
		this.user_roleDTO = user_roleDTO;
	}

}
