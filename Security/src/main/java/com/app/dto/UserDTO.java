package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Agents;
import com.app.model.User;
import com.app.model.User_Role;

public class UserDTO {

	private Long id;
	private String username;
	private String pass;
	private UserInformacionDTO userInformacionDTO;
	private Set<User_RoleDTO> user_roleDTO = new HashSet<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String pass, UserInformacionDTO userInformacionDTO,
			Set<User_RoleDTO> user_roleDTO) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.userInformacionDTO = userInformacionDTO;
		this.user_roleDTO = user_roleDTO;
	}

	public UserDTO(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.pass = u.getPass();
		if (u.getUserInformacion() != null)
			this.userInformacionDTO = new UserInformacionDTO(u.getUserInformacion());

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

	public Set<User_RoleDTO> getUser_roleDTO() {
		return user_roleDTO;
	}

	public void setUser_roleDTO(Set<User_RoleDTO> user_roleDTO) {
		this.user_roleDTO = user_roleDTO;
	}

	public UserInformacionDTO getUserInformacionDTO() {
		return userInformacionDTO;
	}

	public void setUserInformacionDTO(UserInformacionDTO userInformacionDTO) {
		this.userInformacionDTO = userInformacionDTO;
	}

}
