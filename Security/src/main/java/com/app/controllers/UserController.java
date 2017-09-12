package com.app.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.model.ListActivateUser;
import com.app.model.Role;
import com.app.model.StausLogin;
import com.app.model.User;
import com.app.model.UserProfile;
import com.app.model.UserRole;
import com.app.repository.ListActivatUserRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserProfileRepository;
import com.app.repository.UserRepository;
import com.app.repository.UserRoleRepository;
import com.app.security.TokenUtils;
import com.app.util.HendlerLogs;
import com.app.security.XssAttacks;
import com.app.services.MyMailSenderService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserRepository userResponse;
	
	@Autowired
	ListActivatUserRepository listActivatUserRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	public MyMailSenderService mailSender;

	/**
	 * Komentare komentare komentare
	 * @param loginDTO
	 * @param token1
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> loginKorisnik(@RequestBody LoginDTO loginDTO, CsrfToken token1) {
		Map<String, Object> model = new HashMap<>();
		System.out.println("1");
		try{
			User user = userResponse.findByUsername(loginDTO.getUsername());
			System.out.println(user.getUsername());
			ListActivateUser listActivate = listActivatUserRepository.findByUser(user);
			
			if(listActivate == null) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
				Authentication authentication = authenticationManager.authenticate(token);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

				System.out.println(token1.getToken() + " ! " + token1.getHeaderName());
				
				
				
				Date date = new Date();
				
				ListActivateUser listActivateUser = new ListActivateUser();
				listActivateUser.setPrivateKey(token1.getToken());
				listActivateUser.setDateLogin(date);
				listActivateUser.setUser(user);
				listActivateUser.setStatusLogin(StausLogin.ACTIVE);
				listActivatUserRepository.save(listActivateUser);

		        model.put("csrf", token1);
		        model.put("fName", user.getUserProfile().getFirstName());
		        model.put("lName", user.getUserProfile().getLastName());
		        model.put("jwt", tokenUtils.generateToken(details));
		        model.put("role", "agent");
		        model.put("date", date);
		        model.put("statusLog", "ACTIVE");

				return new ResponseEntity<>(model, HttpStatus.OK);
			} else {
				model.put("error", "loggigAccept");
				
				//TODO Email korisniku
				System.out.println("1 + 1");
				mailSender.sendMail(user.getUserProfile().getEmail(), "Login Attack", "Neko je pokusao da se ulogije na vas profil sa IP: 123.465.789");
				
				//trebao bi da javi na email da je neko pokusao da se prijavi dodatno
				//Treba ovo ispraviti da dodaje jos
				
				System.out.println("proslao mejl");
				HendlerLogs.saveLog("multipate_attempt", loginDTO.getUsername(), null);
				
				return new ResponseEntity<>(model, HttpStatus.OK);
			}
			
			
		} catch(Exception ex) {
			model.put("error", "nesto ne valja " + ex.getLocalizedMessage() + " i ne valja " + ex.getMessage());
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
	}
	
	/**
	 * komentare komentare komentare
	 * @param request
	 * @param response
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/logout/{username}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> logOut(final HttpServletRequest request, final HttpServletResponse response, @PathVariable String username) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userResponse.findByUsername(username);
		ListActivateUser list = listActivatUserRepository.findByUser(user);
		System.out.println(list.getPrivateKey());
		
	    if (auth != null){
	    	listActivatUserRepository.delete(list);
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration/{type}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> registration(@RequestBody UserDTO userDTO, @PathVariable String type, Principal principle) {
		System.out.println("radi");
		
		Map<String, Object> model = new HashMap<>();
		
		User prem = userResponse.findByUsername(principle.getName());
		System.out.println(userDTO.getUsername());
		if(!prem.getUserRole().getRole().getName().equals("ADMIN")){
			model.put("error", false);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		Role role;
		UserRole userRole = new UserRole();
		
		if(type.equals("AGENT")){
			System.out.println("usao ovde kako treba");
			try{
				System.out.println("usao ovde kako treba");
				UserProfile userProfile = userProfileRepository.findOne(Long.parseLong("1"));

				user.setPassword(encoder.encode(userDTO.getPassword()));
				user.setUsername(userDTO.getUsername());
				user.setUserProfile(userProfile);

				role = roleRepository.findByName("AGENT");

				userRole.setUser(user);
				userRole.setRole(role);

				userResponse.save(user);
				userRoleRepository.save(userRole);
				model.put("save", true);
			} catch (Exception e){
				
				
				System.out.println("duplikat " + e);
				model.put("save", false);
				model.put("error", "duplicate_attribu");
			}
			
		} else if(type.equals("OPERATOR")){
			try{
				UserProfile userProfile = new UserProfile();
				
				userProfile.setEmail(userDTO.getUserProfile().getEmail());
				userProfile.setFirstName(userDTO.getUserProfile().getFirstName());
				userProfile.setFirstName(userDTO.getUserProfile().getLastName());
				
				user.setPassword(encoder.encode(userDTO.getPassword()));
				user.setUsername(userDTO.getUsername());
				user.setUserProfile(userProfile);
				
				role = roleRepository.findByName("OPERATOR");
				
				userRole.setRole(role);
				userRole.setUser(user);
				
				userProfileRepository.save(userProfile);
				userResponse.save(user);
				userRoleRepository.save(userRole);
				
				mailSender.sendMail(user.getUserProfile().getEmail(), "Registration notification", "Your are sucessful registered by admin " + prem.getUserProfile().getFirstName() + ". Registration date: "+new Date().toString());
				
				model.put("save", true);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("duplikat " + e);
				HendlerLogs.saveLog("duplicate_key", principle.getName(), userDTO.getUsername() + " | " + userDTO.getUserProfile().getEmail());
				model.put("save", false);
				model.put("error", "duplicate_attribu");
			}
			
		}
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}


	/**
	 * kreirano kreirano kreirano
	 * @param userDTO
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody UserDTO userDTO, Principal principal) {
		Map<String, Object> model = new HashMap<>();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userResponse.findByUsername(principal.getName());
		if(user.getUserRole().getRole().getName().equals("ADMIN") || user.getUserRole().getRole().getName().equals("OPERATOR")){
			if(XssAttacks.isHtml(userDTO.getPassword())){
				model.put("error", "XSS ATTACK");
				return new ResponseEntity<>(model, HttpStatus.OK);
			}
			
			user.setPassword(encoder.encode(userDTO.getPassword()));
			
			userResponse.save(user);
			model.put("update", true);
		}
		return new ResponseEntity<>(model, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/active/agent", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> activeAgent() {
		Map<String, Object> model = new HashMap<>();
		
		List<ListActivateUser> listActiveUser = listActivatUserRepository.findAll();
		System.out.println(listActiveUser.get(0).getUser().getUsername());
		model.put("list", listActiveUser);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
    }
	

	@RequestMapping(value = "/testiranje", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Map<String, Object>> provera(@Valid @RequestBody LoginDTO loginDTO) {
		Map<String, Object> model = new HashMap<>();
		
		String username = loginDTO.getUsername();
		
		if(XssAttacks.isHtml(loginDTO.getUsername())){
			System.out.println("XSS DETEKTOVAN");
			model.put("error", "XSS ATTACK");
	        
		} else {
			User user = userResponse.findByUsername(username);
			System.out.println(userResponse.findByUsername(username));
			model.put("result", user.getUsername());
	        
		}
		
		return new ResponseEntity<>(model, HttpStatus.OK);
    }
	
}
