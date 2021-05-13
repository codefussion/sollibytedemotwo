package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.DataSpecialist;

public interface DataSpecialistRepository extends JpaRepository<DataSpecialist, Integer> {

	//find data specialist using phone number
	DataSpecialist findByPhoneNumber(String phoneNumber);

}
