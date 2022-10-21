package com.lib.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lib.repository.RoleRepository;
import com.lib.repository.UserRepository;
import com.lib.controller.dto.RegisterRequest;
import com.lib.domain.Role;
import com.lib.domain.User;
import com.lib.domain.enums.UserRole;
import com.lib.exception.ConflictException;
import com.lib.exception.ResourceNotFoundException;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public void registerUser(RegisterRequest request) {
		//kayit formunda girilen username unique mi ?
		if (userRepository.existsByUserMail(request.getUserMail())) {
			throw new ConflictException("Mail is already registered");
		}
		//create de otomatik olarak role kismina Student ekliyorum
		Role role = roleRepository.findByName(UserRole.ROLE_USER).
				orElseThrow(()->new ResourceNotFoundException("Mail Not Found"));
		
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUserMail(request.getUserMail());
		user.setPhoneNumber(request.getPhoneNumber());

		//role set ediliyor
		user.setRoles(roles);
		//password
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
	}
}
