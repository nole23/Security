package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Premission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "premission", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
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

	public Set<RolePremission> getRolePremission() {
		return rolePremission;
	}

	public void setRolePremission(Set<RolePremission> rolePremission) {
		this.rolePremission = rolePremission;
	}

}
