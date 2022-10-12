package com.tpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpro.domain.Student;
import com.tpro.dto.StudentDTO;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);
	//Spring Data JPA icinde existById() var fakat Spring Data JPA bize 
	//sondaki eki istedigimiz degisken ismi ile
	//degistirmemize izin veriyor (edited) 

	List<Student> findByLastName(String lastName);//kendi ekliyor

	
	
	//LPQL ile yazalim  kendimiz olusturuyoruz burayi
	//@Query-> ben olusturuyorum
	@Query("SELECT s from Student s WHERE s.grade=:pGrade")	//=:pGrade biyerden deger alacagin : demek
	List<Student> findAllEqualsGrade(@Param("pGrade")Integer grade);
	
	//Native query(SQL)
	@Query(value="SELECT * from Student s WHERE s.grade=:pGrade", nativeQuery=true)
	List<Student> findAllEqualsGradeWithSQL(@Param("pGrade")Integer grade);

	//JPQL
	@Query("SELECT new com.tpro.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")//yeni obje olusturuyoruz full yol yaz
	Optional<StudentDTO> findStudentDTOById(@Param("id")Long id);//Optional ya gelmez ise icin kullanilan yapi

}
	