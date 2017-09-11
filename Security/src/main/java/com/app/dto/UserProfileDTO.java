package com.app.dto;

import java.util.Set;

import com.app.model.UserProfile;

public class UserProfileDTO {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private Set<UserDTO> userDTO;

	public UserProfileDTO() {
	}

	public UserProfileDTO(Long id, String email, String firstName, String lastName, Set<UserDTO> userDTO) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userDTO = userDTO;
	}

	public UserProfileDTO(UserProfile userProfile) {
		this.id = userProfile.getId();
		this.email = userProfile.getEmail();
		this.firstName = userProfile.getFirstName();
		this.lastName = userProfile.getLastName();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<UserDTO> getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(Set<UserDTO> userDTO) {
		this.userDTO = userDTO;
	}

}
