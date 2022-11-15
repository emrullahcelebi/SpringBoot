package com.hb10.idgenerrationstratgy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book10 extends BaseEntity {

//@Id
//private int id;
private String name;




public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "Book09 [id=" + getId() + ", name=" + name + "]";
}

	
}
