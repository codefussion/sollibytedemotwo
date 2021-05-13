package com.solidarity.utility;

import com.solidarity.model.Grade;
import com.solidarity.model.Learner;
import com.solidarity.model.LearningArea;

public interface CalculateResults {

	//generate the overall Grade mean score all learning areas combined
	public int generateOveralGradeMinScore(Grade grade,int term);
	
	//generate the overall Grade mean score per learning area
	public int generateOveralGradeMinPerLearningArea(Grade grade,LearningArea learningArea,int term);
	
	//generate overall learner's  score all learning areas combined
	public int generateLearnerOveralScore(Learner learner,int term);
	
	//generate overall learner's performance per learning area
	public int generateLearnerOveralScorePerLearningArea(Learner learner,LearningArea learningArea,int term,Grade grade);

}
