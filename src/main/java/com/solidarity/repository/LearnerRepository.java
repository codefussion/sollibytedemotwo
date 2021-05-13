package com.solidarity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Grade;
import com.solidarity.model.Learner;

public interface LearnerRepository extends JpaRepository<Learner, Integer> {

	//find learner using nemiss number
	List<Learner> findByNemissNumber(String nemissNumber);
	
	//find learner using  grade id
	List<Learner> findByGrade(Grade grade);
	
	//find learner using learner id
	Learner findByLearnerId(int learnerId);
	
	
}
