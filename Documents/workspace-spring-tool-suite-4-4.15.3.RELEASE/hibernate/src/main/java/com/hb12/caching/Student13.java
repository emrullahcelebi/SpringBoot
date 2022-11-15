package com.hb12.caching;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="Student13")//hem oku hem yaz
public class Student13 {
	
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="student_name",nullable=false)
	private String name;
	private int mathGrade;
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public int getmathGrade() {
		return mathGrade;
	}



	public void setmathGrade(int mathGrade) {
		mathGrade = mathGrade;
	}



	public Long getId() {
		return id;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Student12 [id=" +id + ", name=" + name + ", mathGrade=" + mathGrade;
	}



}
