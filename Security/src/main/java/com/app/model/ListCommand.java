package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ListCommand {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private CommandOfUser commandOfUser;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User_Role userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommandOfUser getCommandOfUser() {
		return commandOfUser;
	}

	public void setCommandOfUser(CommandOfUser commandOfUser) {
		this.commandOfUser = commandOfUser;
	}

	public User_Role getUserRole() {
		return userRole;
	}

	public void setUserRole(User_Role userRole) {
		this.userRole = userRole;
	}

}
