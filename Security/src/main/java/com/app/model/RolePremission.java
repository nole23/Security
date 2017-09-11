package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RolePremission {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Role role;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Premission premission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Premission getPremission() {
		return premission;
	}

	public void setPremission(Premission premission) {
		this.premission = premission;
	}

}
