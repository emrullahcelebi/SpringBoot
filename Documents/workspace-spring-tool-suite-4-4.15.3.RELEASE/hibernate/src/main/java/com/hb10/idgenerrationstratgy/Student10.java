package com.hb10.idgenerrationstratgy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Student10 extends BaseEntity {

	
	//BaseEntity de @Id annotation oldugu icin artik asagidaki@Id ihtiyac yok
	//@Id
	//@GeneratedValue//AUTO
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
	//@GeneratedValue(strategy= GenerationType.SEQUENCE)
	//@GeneratedValue(strategy= GenerationType.TABLE)
	
	
	//@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name="sequence",sequenceName="student_seq", initialValue=1000, allocationSize =10)
	
	
//bcb5698a-ee15-48f5-9df3-2a6005e6f54f
//	@GeneratedValue(generator="UUID")
//	@GenericGenerator(name="UUID",strategy="uuid2")
//	private String id;
	
	//private Long id;
	
	@Column(name="student_name",nullable=false)
	private String name;
	private int grade;
	
	
	
	
	
//	public String getId() {
//		return id;
//	}
//
//
//
//	public void setId(String id) {
//		this.id = id;
//	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getGrade() {
		return grade;
	}



	public void setGrade(int grade) {
		this.grade = grade;
	}


	@Override
	public String toString() {
		return "Student10 [id=" +getId() + ", name=" + name + ", grade=" + grade;
	}
	
	
}
