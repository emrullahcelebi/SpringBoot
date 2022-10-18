package com.library.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.library.domain.enums.UserRoles;



public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

		 private Integer id;
	@Enumerated(EnumType.STRING)// 
	@Column (length=30,nullable=false)
		 private UserRoles name;


	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Roller[name="+name+"]";
		}
}
