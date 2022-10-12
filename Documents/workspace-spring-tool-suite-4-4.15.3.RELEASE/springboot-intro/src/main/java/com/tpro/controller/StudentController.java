package com.tpro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpro.domain.Student;
import com.tpro.dto.StudentDTO;
import com.tpro.service.StudentService;

@RestController
@RequestMapping("/students")//burada /students bunla geleni buradan devam et
public class StudentController {

	@Autowired
	private StudentService studentService;//new siz obje injection yaptik
	
	@GetMapping("/welcome")//localhost:8080/students/welcome ile gleirsen bu method calisir
	public String welcome() {
		return "Welcome to Student Controller";
	}
	
	//Get All Students
	@GetMapping //get ile gelirse bura calisir
	public ResponseEntity<List<Student>> getAll() {//donen degerler Student oldugu icin List kullaniyoruz
		List<Student>students= studentService.getAll();
		return ResponseEntity.ok(students);//2 bilgi gonderiyor 1.status code 404 200 gibi, digeri liste
											//ResponseEntity ile isimiz kolaylasiyor "ok"->hata kodlari. gibi farkli methodlari kullanabiliyoruz
	}
	
	//Create new Student
	@PostMapping // post ile gelirse bura calisir
	public ResponseEntity<Map<String,String>> creatStudent(@Valid @RequestBody Student student){
		//@RequestBody json formatini bizim objeye ceviriyor
		//@Valid gelen json un degerlerini   bizim istedigimiz degerler mi kontrol ediyor
		studentService.createStudent(student);
		Map<String,String> map=new HashMap<>();
		map.put("message", "Student is created successfuly");//key-value
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.CREATED);//CREATED olusturuldu
	}
	
	//get a Student by ID by RewuestParam
	@GetMapping("/query") 
	public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
		Student student= studentService.findStudent(id);
		return ResponseEntity.ok(student);
		
	}
	
	//get a Student by ID by PathVariable
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
		Student student= studentService.findStudent(id);
		return ResponseEntity.ok(student);
	}
	
	//Delete Student
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
		studentService.deleteStudent(id);
		Map<String,String> map=new HashMap<>();
		map.put("message", "Student is deleted successfuly");//key-value
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	//Update Student DTO kullanarak
	@PutMapping("{id}") //localhost:8080/students/1
	public ResponseEntity<Map<String,String>> updateStudent(@PathVariable("id") Long id,@RequestBody StudentDTO studentDTO){
		studentService.updateStudent(id,studentDTO);
		Map<String,String> map=new HashMap<>();
		map.put("message", "Student is updated successfuly");//key-value
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	//pageable
	@GetMapping("/page")
	public ResponseEntity<Page<Student>> getAllWithPage(@RequestParam("page") int page,
														@RequestParam("size") int size,
														@RequestParam("sort") String prop,
														@RequestParam("direction") Direction direction){
		
		Pageable pageable=PageRequest.of(page, size,Sort.by(direction,prop));
		Page<Student>studentPage= studentService.getAllWithPage(pageable);
		return ResponseEntity.ok(studentPage);
	}
	
	//Get By LastName
	@GetMapping("/querylastname")
	public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName")String lastName){
		List<Student> list = studentService.findStudent(lastName);
		return ResponseEntity.ok(list);
	}
	
	//GetALL studentBy grade(JPQL)
	@GetMapping("/grade/{grade}")
	public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade")Integer grade){
		List<Student> list = studentService.findAllEqualsGrade(grade);
		return ResponseEntity.ok(list);
		}

	//DB den direk DTO olarak datami alsam?
	@GetMapping("/query/dto")
	public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id")Long id){
		StudentDTO studentDTO = studentService.findStudentDTOById(id);
		return ResponseEntity.ok(studentDTO);
		
	}
	
	
	
	
	
	
}
