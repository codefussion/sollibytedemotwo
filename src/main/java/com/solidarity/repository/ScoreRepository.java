package com.solidarity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Learner;
import com.solidarity.model.Score;
import com.solidarity.model.SubStrand;

public interface ScoreRepository extends JpaRepository<Score, Integer>{

	//find score using learner id and subStrand
	Score findByLearnerAndSubStrand(Learner learner,SubStrand subStrand);

	
	//find score using subStrand id
	
	List<Score> findBySubStrand(SubStrand subStrand);


	//find Score using learner id
	List<Score> findByLearner(Learner learner);
}
