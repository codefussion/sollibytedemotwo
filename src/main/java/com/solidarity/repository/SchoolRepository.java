package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.School;

public interface SchoolRepository extends JpaRepository<School , Integer>{
	
	//find school using codeNumber
	School findByCodeNumber(String codeNumber);
	
	//find school using schoolName
	School findBySchoolName(String schoolName);
	
	//find School using schoolId
	School findBySchoolId(int schoolId);

}
