package com.tpro.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tpro.domain.Student;
import com.tpro.dto.StudentDTO;
import com.tpro.exception.ConflictException;
import com.tpro.exception.ResourceNotFoundException;
import com.tpro.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired //bean olusturmaya aday olarak bekliyor bri obje ile surekli kullandiriyor
	private StudentRepository studentRepository;

	public List<Student> getAll() {
	return studentRepository.findAll();
	}

	//Create Student
	public void createStudent(Student student) {
		if (studentRepository.existsByEmail(student.getEmail())) {//git bak boyle bir email var mi bende
			throw new ConflictException("Email is already exist!");
		}
		studentRepository.save(student);
	}

	
	//find Student By Id
	public Student findStudent(Long id) {
		return studentRepository.findById(id).
		orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+id));
	}

	public void deleteStudent(Long id) {
		Student student = findStudent(id);
		studentRepository.delete(student);
		
	}

	//Update Student
	public void updateStudent(Long id, StudentDTO studentDTO) {
		boolean emailExist= studentRepository.existsByEmail(studentDTO.getEmail());
		//girilen mail bendeki database de mevcut mu 
		//buradan true gelirse hata vermeliyiz 
		Student student= findStudent(id);//anlik olarak giris yapan kullanici bilgilerini student objesine set ediyorum
		//email exist mi (var mi) ve anlik olarak gelen kullaniciya mi ait bunun kontrolu
		if(emailExist && !studentDTO.getEmail().equals(student.getEmail())) {
			throw new ConflictException("Email is already exist");
		}
		
		//yeni girisleri pojo classimiza set ediyoruz
		student.setName(studentDTO.getFirstName());
		student.setLastName(student.getLastName());
		student.setGrade(studentDTO.getGrade());
		student.setEmail(studentDTO.getEmail());
		student.setPhoneNumber(studentDTO.getPhoneNumber());
		
		
		//save islemi
		studentRepository.save(student);
	}

	public Page<Student> getAllWithPage(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public List<Student> findStudent(String lastName) {
		
		return studentRepository.findByLastName(lastName);
	}

	public List<Student> findAllEqualsGrade(Integer grade) {
		
		return studentRepository.findAllEqualsGrade(grade);
	}

	public StudentDTO findStudentDTOById(Long id) {
		return studentRepository.
				findStudentDTOById(id).
				orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+id));
	
	}
	
	


}
