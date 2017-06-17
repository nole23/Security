package com.app.dto;

import com.app.model.UserInformacion;

public class UserInformacionDTO {

	private Long id;
	private String email;
	private String lName;
	private String fName;

	public UserInformacionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInformacionDTO(Long id, String email, String lName, String fName) {
		super();
		this.id = id;
		this.email = email;
		this.lName = lName;
		this.fName = fName;
	}

	public UserInformacionDTO(UserInformacion ui) {
		this.id = ui.getId();
		this.email = ui.getEmail();
		this.lName = ui.getlName();
		this.fName = ui.getfName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

}
