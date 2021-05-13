package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="learning_area")
public class LearningArea {
	// attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int learningAreaId;
	
	private String learningAreaName;

	@ManyToMany(mappedBy = "learning_areas")
	private List<Learner> learner;

	// thinking about this relationship
	@ManyToOne
	@JoinColumn(name = "class_teacher_id")
	private ClassTeacher class_teacher;

	@ManyToMany(mappedBy = "learning_areas")
	private List<SubjectTeacher> subjectTeachers;

	@OneToMany(mappedBy = "learningArea")
	private List<Strand> strands;

	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	private  int averageScore =0;
	private String rubric;
	private int overalTermOneScore;
	private int overalTermTwoScore;
	private int overalTermThreeScore;
	
	// default constructor
	public LearningArea() {
		super();

	}

	// Constructor using fields
	public LearningArea(int learning_area_id, String learning_area_name, List<Learner> learner,
			ClassTeacher class_teacher, List<SubjectTeacher> subject_teachers, List<Strand> stands, Grade grade) {
		super();
		this.learningAreaId = learning_area_id;
		this.learningAreaName = learning_area_name;
		this.learner = learner;
		this.class_teacher = class_teacher;
		this.subjectTeachers = subject_teachers;
		this.strands = stands;
		this.grade = grade;
	}

	// getters and setters
	public int getLearningAreaId() {
		return learningAreaId;
	}

	public void setLearningAreaId(int learning_area_id) {
		this.learningAreaId = learning_area_id;
	}

	public String getLearningAreaName() {
		return learningAreaName;
	}

	public void setLearningAreaName(String learning_area_name) {
		this.learningAreaName = learning_area_name;
	}

	public List<Learner> getLearner() {
		return learner;
	}

	public void setLearner(List<Learner> learner) {
		this.learner = learner;
	}

	public ClassTeacher getClass_teacher() {
		return class_teacher;
	}

	public void setClassTeacher(ClassTeacher class_teacher) {
		this.class_teacher = class_teacher;
	}

	public List<SubjectTeacher> getSubjectTeachers() {
		return subjectTeachers;
	}

	public void setSubjectTeachers(List<SubjectTeacher> subject_teachers) {
		this.subjectTeachers = subject_teachers;
	}

	public List<Strand> getStrands() {
		return strands;
	}

	public void setStrands(List<Strand> stands) {
		this.strands = stands;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public int getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(int averageScore) {
		this.averageScore = averageScore;
	}

	public String getRubric() {
		return rubric;
	}

	public void setRubric(String rubric) {
		this.rubric = rubric;
	}

	public void setClass_teacher(ClassTeacher class_teacher) {
		this.class_teacher = class_teacher;
	}

	public int getOveralTermOneScore() {
		return overalTermOneScore;
	}

	public void setOveralTermOneScore(int overalTermOneScore) {
		this.overalTermOneScore = overalTermOneScore;
	}

	public int getOveralTermTwoScore() {
		return overalTermTwoScore;
	}

	public void setOveralTermTwoScore(int overalTermTwoScore) {
		this.overalTermTwoScore = overalTermTwoScore;
	}

	public int getOveralTermThreeScore() {
		return overalTermThreeScore;
	}

	public void setOveralTermThreeScore(int overalTermThreeScore) {
		this.overalTermThreeScore = overalTermThreeScore;
	}
}
