package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Klasa korisnik koja opsluzuje i agente i korisnike samo sto korisnici imaju
 * referencu na klasu informacije i tu se nalaze licni podaci korisnika
 * 
 * @author X
 *
 */

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private UserProfile userProfile;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private UserRole userRole;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListActivateUser> listActivateUser = new HashSet<ListActivateUser>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Set<ListActivateUser> getListActivateUser() {
		return listActivateUser;
	}

	public void setListActivateUser(Set<ListActivateUser> listActivateUser) {
		this.listActivateUser = listActivateUser;
	}
}
