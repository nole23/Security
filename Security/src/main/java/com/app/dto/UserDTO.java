package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Agents;
import com.app.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String pass;
	private UserInformacionDTO userInformacionDTO;
	private User_RoleDTO role;
	private Set<AgentDTO> agentDTO = new HashSet<AgentDTO>();

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String pass, UserInformacionDTO userInformacionDTO, User_RoleDTO role,
			Set<AgentDTO> agentDTO) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.userInformacionDTO = userInformacionDTO;
		this.role = role;
		this.agentDTO = agentDTO;
	}

	public UserDTO(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.pass = u.getPass();
		if (u.getUserInformacion() != null)
			this.userInformacionDTO = new UserInformacionDTO(u.getUserInformacion());
		if (u.getRole() != null)
			this.role = new User_RoleDTO(u.getRole());
		if (u.getAgents() != null) {
			this.agentDTO = new HashSet<AgentDTO>();
			for (Agents a : u.getAgents()) {
				this.agentDTO.add(new AgentDTO(a));
			}
		}
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

	public Set<AgentDTO> getAgentDTO() {
		return agentDTO;
	}

	public void setAgentDTO(Set<AgentDTO> agentDTO) {
		this.agentDTO = agentDTO;
	}

}
