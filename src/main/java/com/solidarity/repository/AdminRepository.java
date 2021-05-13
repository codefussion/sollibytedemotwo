package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	//find Admin using phone Number
	Admin findByPhoneNumber(String phoneNumber);

}
