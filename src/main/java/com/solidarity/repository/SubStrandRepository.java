package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.SubStrand;


public interface SubStrandRepository extends JpaRepository<SubStrand, Integer> {

	
	//find subStrand using substrandId
	SubStrand findBySubStrandId(int subStrandId);
}
