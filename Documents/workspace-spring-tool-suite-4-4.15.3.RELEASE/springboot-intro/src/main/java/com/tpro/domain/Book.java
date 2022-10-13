package com.tpro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Entity
public class Book {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bookName")// JSON ciktida isminin bookName olmasini istedim
	private String name;

	@JsonIgnore//sonsuz donguye girmemesi icin 
	//kisacasi tekrar student classi na gitmemesi icin bu annotation u kullandik
	@ManyToOne
	@JoinColumn(name="student_id")
		private Student student;//iliskilendirmek icin

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}
	
	
}
