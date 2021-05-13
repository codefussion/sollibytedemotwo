package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Enroller;

public interface EnrollerRepository extends JpaRepository<Enroller, Integer> {
	
  //finding enroller using phone number
   Enroller findByPhoneNumber(String phoneNumber);
}
