package com.hb10.idgenerrationstratgy;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="sequence",sequenceName="student_seq", initialValue=1000, allocationSize =10)
	private Long id;
	
	@Column(name="create_on")
	private LocalDateTime creatOn= LocalDateTime.now();

	public LocalDateTime getCreatOn() {
		return creatOn;
	}

	public void setCreatOn(LocalDateTime creatOn) {
		this.creatOn = creatOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
