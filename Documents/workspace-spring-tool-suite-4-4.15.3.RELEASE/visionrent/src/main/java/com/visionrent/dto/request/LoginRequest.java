package com.visionrent.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter@AllArgsConstructor
public class LoginRequest {

	@Email(message="Please provide a valid email")
	private String email;
	
	@NotBlank(message="Please provide a valid password")
	private String password; 
}
