package com.app.dto;

import com.app.model.Role;
import com.app.model.User;
import com.app.model.User_Role;

public class User_RoleDTO {

	private Long id;
	private User user;
	private Role role;
	
	public User_RoleDTO() {}
	
	public User_RoleDTO(Long id, User user, Role role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	
	public User_RoleDTO(User_Role userRole) {
		this.id = userRole.getId();
		this.user = userRole.getUser();
		this.role = userRole.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User_RoleDTO [id=" + id + ", user=" + user + ", role=" + role
				+ "]";
	}
	
	
}
