package com.app.dto;

import com.app.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private UserProfileDTO userProfile;

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String password, UserProfileDTO userProfileDTO) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userProfile = userProfileDTO;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		if (user.getUserProfile() != null)
			this.userProfile = new UserProfileDTO(user.getUserProfile());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserProfileDTO getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileDTO userProfile) {
		this.userProfile = userProfile;
	}

}
