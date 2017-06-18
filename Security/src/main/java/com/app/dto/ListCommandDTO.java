package com.app.dto;

import com.app.model.ListCommand;

public class ListCommandDTO {

	private CommandOfUserDTO commandOfUserDTO;
	private User_RoleDTO userRoleDTO;

	public ListCommandDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListCommandDTO(CommandOfUserDTO commandOfUserDTO, User_RoleDTO userRoleDTO) {
		super();
		this.commandOfUserDTO = commandOfUserDTO;
		this.userRoleDTO = userRoleDTO;
	}

	public ListCommandDTO(ListCommand lc) {
		if (lc.getCommandOfUser() != null)
			this.commandOfUserDTO = new CommandOfUserDTO(lc.getCommandOfUser());
		if (lc.getUserRole() != null)
			this.userRoleDTO = new User_RoleDTO(lc.getUserRole());
	}

	public CommandOfUserDTO getCommandOfUserDTO() {
		return commandOfUserDTO;
	}

	public void setCommandOfUserDTO(CommandOfUserDTO commandOfUserDTO) {
		this.commandOfUserDTO = commandOfUserDTO;
	}

	public User_RoleDTO getUserRoleDTO() {
		return userRoleDTO;
	}

	public void setUserRoleDTO(User_RoleDTO userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}

}
