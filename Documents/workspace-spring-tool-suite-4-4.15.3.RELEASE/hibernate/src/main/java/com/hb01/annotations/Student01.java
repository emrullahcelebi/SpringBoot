package com.hb01.annotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//Ctrl+Shift+O import icin
//Ctrl+ Space
//bu annotationu koydugumuz sinif veritabaninda bir tabloy akarsilikk glecek

@Entity

//Eger tablonun ismi degistirilmek istenirse name attribute ile bir tablo ismi verilir
@Table(name="t_student01")

public class Student01 {

	@Id
	//@Column(name="std_id)--isim vermek icin id ye
	private int id;
	
	//length default=225
	//nullable default =true
	//unique=false /default
	//zorunlu degil ancak customize edebilmek icin gerekli
	@Column(name="student_name",length=100, nullable= false, unique=false)
	private String name;
	
	//veri tabani tablosunda kolon olusturmaz
	//@Transient
	private int grade;

	
	//large object ile buyuk boyutlu datalar tutulabilir
		//@Lob
		//private byte [] image;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return "Student01 [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}
	
	
	
}
