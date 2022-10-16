package com.tpro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tpro.domain.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //enam da zaten 2 deger var
	
	@Enumerated(EnumType.STRING)//Tabloya Enum int degeri ile degil String ifade ile kaydedecek Enum dan donen deger 1-2 gibi Integer oldugundan Stringe ceviriyoruz
	@Column(length=30,nullable=false)
	private UserRole name;
	
	
	@Override
	public String toString() {
		
		return "Role[name="+name+"]";
	}
}

