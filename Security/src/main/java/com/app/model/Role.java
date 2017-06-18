package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL)
	private User_Role user_role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User_Role getUser_role() {
		return user_role;
	}

	public void setUser_role(User_Role user_role) {
		this.user_role = user_role;
	}

}
