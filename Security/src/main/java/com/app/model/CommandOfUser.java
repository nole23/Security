package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CommandOfUser {

	@Id
	@GeneratedValue
	private Long id;
	private String command;

	@OneToMany(mappedBy = "commandOfUser", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListCommand> listCommand = new HashSet<ListCommand>();

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

	public Set<ListCommand> getListCommand() {
		return listCommand;
	}

	public void setListCommand(Set<ListCommand> listCommand) {
		this.listCommand = listCommand;
	}

}
