package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.ListCommand;
import com.app.model.User_Role;

public class User_RoleDTO {

	private Long id;
	private UserDTO userDTO;
	private RoleDTO roleDTO;
	private Set<ListCommandDTO> listCommandDTO = new HashSet<ListCommandDTO>();

	public User_RoleDTO() {
	}

	public User_RoleDTO(Long id, UserDTO userDTO, RoleDTO roleDTO) {
		super();
		this.id = id;
		this.userDTO = userDTO;
		this.roleDTO = roleDTO;
	}

	public User_RoleDTO(User_Role userRole) {
		this.id = userRole.getId();
		if (userRole.getRole() != null)
			this.roleDTO = new RoleDTO(userRole.getRole());

		if (userRole.getListCommand() != null) {
			this.listCommandDTO = new HashSet<ListCommandDTO>();
			for (ListCommand lc : userRole.getListCommand()) {
				this.listCommandDTO.add(new ListCommandDTO(lc));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public Set<ListCommandDTO> getListCommandDTO() {
		return listCommandDTO;
	}

	public void setListCommandDTO(Set<ListCommandDTO> listCommandDTO) {
		this.listCommandDTO = listCommandDTO;
	}

}
