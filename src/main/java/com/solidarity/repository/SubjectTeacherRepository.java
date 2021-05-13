package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.SubjectTeacher;

public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Integer> {

	//find subject Teacher Using phone Number
	SubjectTeacher findByPhoneNumber(String phoneNumber);
	
	//find subject teacher using id
	SubjectTeacher findBySubjectTeacherId(int subjectTeacherid);
}
