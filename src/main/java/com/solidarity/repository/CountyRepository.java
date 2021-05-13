package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.County;

public interface CountyRepository extends JpaRepository<County, Integer> {
  
	County findByCountyNameIgnoreCase(String countyName);
}
