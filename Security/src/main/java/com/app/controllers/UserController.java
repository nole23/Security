package com.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.dto.ResponseMessageDTO;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.model.User;
import com.app.model.UserInformacion;
import com.app.model.User_Role;
import com.app.repository.RoleRepository;
import com.app.repository.UserInformacionRepository;
import com.app.repository.UserRepository;
import com.app.repository.UserRoleRepository;
import com.app.security.TokenUtils;
import com.app.services.UserService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

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

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInformacionRepository userInformacionRepository;
	
	
	/***
	 * 
	 * @param loginDTO
	 *            stores username and password of user
	 * @return returns the web token if data are correct, otherwise returns
	 *         "invalid login" message
	 * @see LoginDTO
	 * @author stefan
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JSONObject> login(@RequestBody LoginDTO loginDTO) {
		try {
			// Perform the authentication
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload user details so we can generate token
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			
			String tok = tokenUtils.generateToken(details);
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(tok);
			
			return new ResponseEntity<JSONObject>(json, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessageDTO> changePass(@RequestBody UserDTO userDTO, Principal principal) {

		// check if password is longer then 5 chars
		if (userDTO.getPass().length() < 5)
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Password must be at least 5 chars long"), HttpStatus.CONFLICT);

		UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
		User user = userRepository.findByUsername(currentUser.getUsername());
		// set new password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPass(encoder.encode(userDTO.getPass()));
		userRepository.save(user);

		return new ResponseEntity<ResponseMessageDTO>(HttpStatus.OK);
	}
	
	
	/**
	 * Dodavanje novog korisnika ukljucujuci i agenta, samo registrovani admin moze da dodaje nekog novog na sistem
	 * 
	 * @param userDTO
	 * @param type
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/registration/{type}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessageDTO> registration(@RequestBody UserDTO userDTO, @PathVariable String type, Principal principal) {
		
		User user = new User();
		UserInformacion ui = new UserInformacion();
		User_Role userRole = new User_Role();
		User admin = userRepository.findByUsername(principal.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(type.equals("admin") || type.equals("operator")) {
			
			String ro; 
			if(type.equals("operator")) {
				ro = "OPERATOR";
			} else {
				ro = "ADMIN";
			}
				
			Role rola = roleRepository.findByName(ro);
			
			ui.setfName(userDTO.getUserInformacionDTO().getfName());
			ui.setlName(userDTO.getUserInformacionDTO().getlName());
			ui.setEmail(userDTO.getUserInformacionDTO().getEmail());
			userInformacionRepository.save(ui);
			
			user.setUsername(userDTO.getUsername());
			user.setPass(encoder.encode(userDTO.getPass()));
			user.setUserInformacion(ui);
			userRepository.save(user);
			

			userRole.setRole(rola);
			userRole.setUser(user);
			userRoleRepository.save(userRole);
			
			//ovde ide registracija za admine i operatere
			return new ResponseEntity<ResponseMessageDTO>(HttpStatus.OK);
		} else if(type.equals("agent")) {
			
			Role rola = roleRepository.findByName("AGENT");
			if(admin.getRole().getRole().getName().equals("ADMIN"))
				return new ResponseEntity<ResponseMessageDTO>(HttpStatus.BAD_REQUEST);

			user.setUsername(userDTO.getUsername());
			user.setPass(encoder.encode(userDTO.getPass()));
			userRepository.save(user);
			
			userRole.setRole(rola);
			userRole.setUser(user);
			userRoleRepository.save(userRole);
			//ovde ide registracija za agente
			return new ResponseEntity<ResponseMessageDTO>(HttpStatus.OK);
		} else {
			
			System.out.println("treba odraditi metodu koja prijavljuje neki gresku");
			//Neko pokusava da naudi
			return new ResponseEntity<ResponseMessageDTO>(HttpStatus.OK);
		}
		
	}
}
