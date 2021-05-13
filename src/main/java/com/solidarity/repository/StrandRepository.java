package com.solidarity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.LearningArea;
import com.solidarity.model.Strand;

public interface StrandRepository extends JpaRepository<Strand, Integer> {

	
	//find strand using strand id
	Strand findByStrandId(int strandId);
	
	//find strand using strand name
	List<Strand>  findByStrandName(String strandName);

	//find Strands using learningArea
	List<Strand> findByLearningArea(LearningArea learningArea);
	
	 //find strand using learning Area id and strandName
	Strand findByLearningAreaAndStrandNameIgnoreCase(LearningArea learningAreaFromInput, String strandName);

}
