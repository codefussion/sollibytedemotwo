package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.ClassTeacher;

public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Integer> {
	
	//find classTeacherUsing phoneNumber
	ClassTeacher findByPhoneNumber(String phoneNumber);
	
	//find class Teacher using classTeacher id
	ClassTeacher findByClassTeacherId(int classTeacherId);

}
