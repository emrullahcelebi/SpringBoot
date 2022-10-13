package com.tpe.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/student")
public class StudentBean01Controller {
	
	@GetMapping(path="/getRequest")
	public String getMethod1() {
		return "getRequest ile getMethod1 calistirildi";
	}
	
	// tight coupling
	@GetMapping(path="/getObjectTight")
	public StudentBean01 getMethod2(){
		return new StudentBean01
				("Ahat Dertsiz", 35, "AD202215");
	}
	
	//louse coupling
	@Autowired
	StudentBean01 studentBean01;//DI yaptik
	@GetMapping(path="/getObjectLoose")
	public StudentBean01 getMethod3(){
		studentBean01.setName("erdal bey");
		studentBean01.setAge(35);
		studentBean01.setId("EB2022Dev");
		
		return studentBean01;
		
	}
	
	//tight copling
	@GetMapping(path="getObjectParametreli1/{school}")
	public StudentBean01 getMethod4(@PathVariable String school) {
		
		return new StudentBean01("osman bey",35,String.format("Ob2022%s35", school));
	}
	
	//louse coupling
	@GetMapping(path="getObjectParametreli2/{school}")
	public StudentBean01 getMethod5(@PathVariable String school) {
		studentBean01.setName("ozgur bey");
		studentBean01.setAge(35);
		studentBean01.setId(String.format("OB2022%s35", school));
			
		return studentBean01;
		}
	
	@Autowired
	@Qualifier(value="studentBean01")//bu stady methodunu studentBean02 den getir
	StudentInterface studentInterface; //injecte ettik
	
	@GetMapping(path="/getStudy")
	public String getMethod6() {
		return studentInterface.study();
	}
	
	
		
	

	
	
	
	
	
}