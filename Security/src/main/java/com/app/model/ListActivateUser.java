package com.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ListActivateUser {

	@Id
	@GeneratedValue
	private Long id; 				// id tabele
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user; 				// id usera
	private Date dateLogin; 		// vreme logovanja
	private StausLogin statusLogin; // status loga {ACTIVE, PRE-ACTIVE}
	private String privateKey; 		// za agente private kljuc koji ce nam sluziti za
									// proveru autoriteta njegovih logova u slucaju
									// da neko uhakuje
									// u njegov log neki podatak

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}

	public StausLogin getStatusLogin() {
		return statusLogin;
	}

	public void setStatusLogin(StausLogin statusLogin) {
		this.statusLogin = statusLogin;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
