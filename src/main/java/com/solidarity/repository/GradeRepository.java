package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Grade;
import com.solidarity.model.School;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	
	//find grade Using stream
	Grade findByGradeStreamIgnoreCase(String gradeStream);
	
	//find grade using number
	Grade findGradeByGradeNumberAndGradeStreamAndSchool(int gradeNumber,String gradeStream, School school);
	
	//find grade using grade Id
	Grade findByGradeId(int gradeId);
	
	//find grade using number stream school and year
	Grade findGradeByGradeNumberAndGradeStreamAndSchoolAndYear(int gradeNumber,String gradeStream, School school,int year);
	
	

}
