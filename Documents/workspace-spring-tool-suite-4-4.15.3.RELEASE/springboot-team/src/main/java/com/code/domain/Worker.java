package com.code.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	
	@NotNull(message="first name can not be null")
	@NotBlank(message="last name can not be white space")
	@Size(min=2, max=25, message="First name '${validatedValue' must be between {min} and {max} long")
	@Column(nullable=false,length=25)
	private String name;
	@Column(nullable=false,length=25)
	private String lastName;
	@Column(nullable=false,length=50)
	private String department;
	@Column(nullable=false,length=15)
	private String phoneNumber;
	
	@Column(nullable=false,length=50,unique=true)//unique=true tek uniq olacak
	@Email(message="Provide valid email")//email in formatini kontrol ediyor
	private String email;
	
	@Column(nullable=false)
	private Integer salary;
	
	
	
	

}
