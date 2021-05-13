package com.solidarity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidarity.model.Grade;
import com.solidarity.model.LearningArea;
import com.solidarity.model.SubjectTeacher;

public interface LearningAreaRepository extends JpaRepository<LearningArea, Integer> {

	//find learning area using grade
	List<LearningArea>  findByGrade(Grade grade);
	
	//find learning area using list of subjectTeachers
	List<LearningArea>  findBySubjectTeachersIn(List<SubjectTeacher> subjectTeachers);
	
	//find learning area using learning area id
	LearningArea findByLearningAreaId(int learningAreaId);

    //find learning area using grade and learning areaName
	LearningArea findByGradeAndLearningAreaName(Grade grade, String learningAreaName);
}
