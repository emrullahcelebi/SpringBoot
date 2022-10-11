package com.tpro.domain;

import java.time.LocalDateTime;

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
@Entity//tablo olusturmak icin
public class Student {//pojo class im 
//pojo class im
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//otomatik id icin
	private Long id;//null wrapper class kullanmamizin sebebi , eger deger atanmaz ise null atansin
	//int atasaydik degeri default olarak 0 olacakti
	
	@NotNull(message="first name can not be null")
	@NotBlank(message="last name can not be white space")
	@Size(min=2, max=25, message="First name '${validatedValue' must be between {min} and {max} long")
	@Column(nullable=false,length=25)//nullable=false bos giremez uzunluk en fazla 25
	private String name;
	@Column(nullable=false,length=25)
	private String lastName;
	@Column
	private Integer grade;
	@Column(nullable=false,length=50,unique=true)//unique=true tek uniq olacak
	@Email(message="Provide valid email")//email in formatini kontrol ediyor
	private String email;
	@Column
	private String phoneNumber;
	
	private LocalDateTime createDate= LocalDateTime.now();

	







	


	

	
}
