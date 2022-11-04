package com.realestate.dto;

import java.util.Set;

import com.realestate.domain.Review;
import com.realestate.domain.Role;
import com.realestate.domain.TourRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;
  
    private String firstName;
    
    private String lastName;

    private String password;
 
    private String phoneNumber;

    private String email;
   
    private String address;
    
    private String zipCode;
    
    private Boolean builtIn;
    
    private Role role;
    
    private Set<Review> reviews;
    
    private Set<TourRequest> tourRequests;


}
