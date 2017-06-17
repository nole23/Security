package com.app.dto;

import com.app.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String pass;
	private UserInformacionDTO userInformacionDTO;
	private User_RoleDTO role;

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String pass, UserInformacionDTO userInformacionDTO, User_RoleDTO role) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.userInformacionDTO = userInformacionDTO;
		this.role = role;
	}

	public UserDTO(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.pass = u.getPass();
		if (u.getUserInformacion() != null)
			this.userInformacionDTO = new UserInformacionDTO(u.getUserInformacion());
		;
		if (u.getRole() != null)
			this.role = new User_RoleDTO(u.getRole());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User_RoleDTO getRole() {
		return role;
	}

	public void setRole(User_RoleDTO role) {
		this.role = role;
	}

	public UserInformacionDTO getUserInformacionDTO() {
		return userInformacionDTO;
	}

	public void setUserInformacionDTO(UserInformacionDTO userInformacionDTO) {
		this.userInformacionDTO = userInformacionDTO;
	}

}
