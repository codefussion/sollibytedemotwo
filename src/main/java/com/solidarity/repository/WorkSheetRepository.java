package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.WorkSheet;

public interface WorkSheetRepository extends JpaRepository<WorkSheet, Integer> {

}
