package com.visionrent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visionrent.domain.Role;
import com.visionrent.domain.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	//olmayan bir tip gelirse nullPointer gelir 
	//Optional ile garanti altina aliyoruz service de or.else.throw ile bunu halledecegiz
	Optional<Role> findByType(RoleType type);
	
	
}
