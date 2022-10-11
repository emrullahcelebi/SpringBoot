package com.tpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpro.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);
	//Spring Data JPA icinde existById() var fakat Spring Data JPA bize 
	//sondaki eki istedigimiz degisken ismi ile
	//degistirmemize izin veriyor (edited) 

}
	