package com.app.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Premission;
import com.app.model.Role;
import com.app.model.RolePremission;
import com.app.model.User;
import com.app.repository.PermissionRepository;
import com.app.repository.RolePermissionRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermisionRepository;
	
	@Autowired
	private PermissionRepository permisionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			Role role = roleRepository.findByName("ROLE_USER");
			return new org.springframework.security.core.userdetails.User(
		              " ", " ", true, true, true, true, 
		              getAuthorities(role));
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, 
				true, getAuthorities(user.getUserRole().getRole()));
	}
	
	
	
    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		// TODO Auto-generated method stub
		return getGrantedAutorities(getPrivileges(role));
	}

	private List<String> getPrivileges(Role role) {
  
        List<String> permission1 = new ArrayList<>();
        
        List<RolePremission> rolPermision = rolePermisionRepository.findById(role.getId());
        
        for (RolePremission rp : rolPermision) {
        	Premission permision = permisionRepository.findOne(rp.getId());
        	permission1.add(permision.getName());
        }
        return permission1;
    }
	
	private List<GrantedAuthority> getGrantedAutorities(List<String> privilege) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities  = new ArrayList<>();
		for(String pp : privilege) {
			authorities.add(new SimpleGrantedAuthority(pp));
		}
		return authorities;
	}
 
}
