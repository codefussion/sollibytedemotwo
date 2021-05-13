package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.HeadTeacher;

public interface HeadTeacherRepository extends JpaRepository<HeadTeacher, Integer> {
	//find HeadTeacher using phone number
	HeadTeacher findByPhoneNumber(String phoneNumber);
}
