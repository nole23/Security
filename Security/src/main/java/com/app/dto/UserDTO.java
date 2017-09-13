package com.app.dto;

import com.app.model.User;

public class UserDTO {

	private String username;
	private String password;
	private UserProfileDTO userProfile;

	public UserDTO() {
	}

	public UserDTO(String username, String password, UserProfileDTO userProfileDTO) {
		super();
		this.username = username;
		this.password = password;
		this.userProfile = userProfileDTO;
	}

	public UserDTO(User user) {
		this.username = user.getUsername();
		if (user.getUserProfile() != null)
			this.userProfile = new UserProfileDTO(user.getUserProfile());

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
