package com.app.dto;

import java.util.Date;

import com.app.model.ListActivateUser;
import com.app.model.StausLogin;

public class ListActivateUserDTO {

	private Long id;
	private UserDTO userDTO;
	private Date dateLogin;
	private StausLogin statusLogin;
	private String privateKey;

	public ListActivateUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListActivateUserDTO(Long id, UserDTO userDTO, Date dateLogin, StausLogin statusLogin, String privateKey) {
		this.id = id;
		this.userDTO = userDTO;
		this.dateLogin = dateLogin;
		this.statusLogin = statusLogin;
		this.privateKey = privateKey;
	}
	
	public ListActivateUserDTO(ListActivateUser listActivate) {
		this.id = listActivate.getId();
		this.dateLogin = listActivate.getDateLogin();
		this.statusLogin = listActivate.getStatusLogin();
		this.privateKey = listActivate.getPrivateKey();
		if(listActivate.getUser() != null){
			this.userDTO = new UserDTO(listActivate.getUser());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
