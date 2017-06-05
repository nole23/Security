package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.repository.RoleRepository;
import com.app.repository.UserRoleRepository;
import com.app.security.TokenUtils;
import com.app.services.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	/***
	 * 
	 * @param loginDTO
	 *            stores username and password of user
	 * @return returns the web token if data are correct, otherwise returns
	 *         "invalid login" message
	 * @see LoginDTO
	 * @author stefan
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		try {
			// Perform the authentication
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload user details so we can generate token
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Invalid login", HttpStatus.NOT_FOUND);
		}
	}

}
