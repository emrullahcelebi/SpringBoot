package com.lib.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lib.repository.RoleRepository;
import com.lib.repository.UserRepository;
import com.lib.controller.dto.RegisterRequest;
import com.lib.controller.dto.UpdateRequestDTO;
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


	public List<User> getAll() {
		
		return userRepository.findAll();
	}


	public void deleteUser(Long id) {
        User user = findUser(id);
        userRepository.delete(user);

   }


	//find User By Id
		public User findUser(Long id) {
			return userRepository.findById(id).
			orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
		}


		//Update User
		public void updateUser(Long id, UpdateRequestDTO updateRequestDTO) {
			boolean emailExist= userRepository.existsByUserMail(updateRequestDTO.getUserMail());
			
			
			User user= findUser(id);
			
			if(emailExist && !updateRequestDTO.getUserMail().equals(user.getUserMail())) {
				throw new ConflictException("Email is already exist");
			}
			
			//yeni girisleri pojo classimiza set ediyoruz
			user.setFirstName(updateRequestDTO.getFirstName());
			user.setLastName(updateRequestDTO.getLastName());
			user.setPassword(updateRequestDTO.getPassword());
			user.setUserMail(updateRequestDTO.getUserMail());
			user.setPhoneNumber(updateRequestDTO.getPhoneNumber());
			
			
			//save islemi
			userRepository.save(user);
		}
}
