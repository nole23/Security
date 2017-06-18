package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String pass;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private UserInformacion userInformacion;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private User_Role role;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Agents> agents = new HashSet<Agents>();

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

	public UserInformacion getUserInformacion() {
		return userInformacion;
	}

	public void setUserInformacion(UserInformacion userInformacion) {
		this.userInformacion = userInformacion;
	}

	public User_Role getRole() {
		return role;
	}

	public void setRole(User_Role role) {
		this.role = role;
	}

	public Set<Agents> getAgents() {
		return agents;
	}

	public void setAgents(Set<Agents> agents) {
		this.agents = agents;
	}

}
