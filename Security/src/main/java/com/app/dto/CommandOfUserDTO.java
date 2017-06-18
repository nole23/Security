package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.CommandOfUser;
import com.app.model.ListCommand;

public class CommandOfUserDTO {

	private Long id;
	private String command;
	private Set<ListCommandDTO> listCommandDTO = new HashSet<ListCommandDTO>();

	public CommandOfUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommandOfUserDTO(Long id, String command, Set<ListCommandDTO> listCommandDTO) {
		super();
		this.id = id;
		this.command = command;
		this.listCommandDTO = listCommandDTO;
	}

	public CommandOfUserDTO(CommandOfUser cof) {
		this.id = cof.getId();
		this.command = cof.getCommand();
		if (cof.getListCommand() != null) {
			this.listCommandDTO = new HashSet<ListCommandDTO>();
			for (ListCommand lc : cof.getListCommand()) {
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

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Set<ListCommandDTO> getListCommandDTO() {
		return listCommandDTO;
	}

	public void setListCommandDTO(Set<ListCommandDTO> listCommandDTO) {
		this.listCommandDTO = listCommandDTO;
	}

}
