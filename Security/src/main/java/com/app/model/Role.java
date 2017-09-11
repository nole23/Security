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
public class Role {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<UserRole> userRole = new HashSet<UserRole>();

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RolePremission> rolePremission = new HashSet<RolePremission>();

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

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<RolePremission> getRolePremission() {
		return rolePremission;
	}

	public void setRolePremission(Set<RolePremission> rolePremission) {
		this.rolePremission = rolePremission;
	}

}
