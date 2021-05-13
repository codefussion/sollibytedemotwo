package com.solidarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Learner;
import com.solidarity.model.ResultRecord;

public interface ResultRecordRepository extends JpaRepository<ResultRecord, Integer>  {

	//find Result record using learnerId and learningAreaName
	ResultRecord findByLearnerAndLearningAreaName(Learner learnerInGrade, String learningAreaName);

	

}
