package com.solidarity.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Learner")
public class Learner {
	// attributes and relationship
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int learnerId;
	
	
	@ManyToOne
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	@ManyToOne
	@JoinColumn(name = "grade_id", nullable = false)
	private Grade grade;
	private String firstName;

	private String secondName;
	private String surName;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinTable(name = "learner_learning_area", joinColumns = @JoinColumn(name = "learner_id"), inverseJoinColumns = @JoinColumn(name = "learning_area_id"))
	private List<LearningArea> learning_areas;
	
	private String nemissNumber;
	
	@OneToMany(mappedBy="learner",cascade = CascadeType.PERSIST)
	private List<Score> scores;
	
	private Integer overalPoint;
	
	@OneToMany(mappedBy="learner")
	private List<ResultRecord> resultRecords;
	
	private Integer indicateEvaluation;
	
	
	// default constructor
	public Learner() {
		super();
	}

	// constructor using fields
	public Learner(int learner_id, School school, Grade grade, String first_name, String second_name, String sir_name,
			List<LearningArea> learning_areas ,String nemissNumber) {
		super();
		this.learnerId = learner_id;
		this.school = school;
		this.grade = grade;
		this.firstName = first_name;
		this.secondName = second_name;
		this.surName = sir_name;
		this.learning_areas = learning_areas;
		this.nemissNumber =nemissNumber;
	}

	// getters and setters
	
	
	public int getLearnerId() {
		return learnerId;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public void setLearnerId(int learner_id) {
		this.learnerId = learner_id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String second_name) {
		this.secondName = second_name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String sir_name) {
		this.surName = sir_name;
	}

	public List<LearningArea> getLearning_areas() {
		return learning_areas;
	}

	public void setLearning_areas(List<LearningArea> learning_areas) {
		this.learning_areas = learning_areas;
	}

	public String getNemissNumber() {
		return nemissNumber;
	}

	public void setNemissNumber(String nemissNumber) {
		this.nemissNumber = nemissNumber;
	}
	public int getOveralPoint() {
		return overalPoint;
	}

	public void setOveralPoint(int overalPoint) {
		this.overalPoint = overalPoint;
	}

	public List<ResultRecord> getResultRecords() {
		return resultRecords;
	}

	public void setResultRecords(List<ResultRecord> resultRecords) {
		this.resultRecords = resultRecords;
	}

	public Integer getIndicateEvaluation() {
		return indicateEvaluation;
	}

	public void setIndicateEvaluation(Integer indicateEvaluation) {
		this.indicateEvaluation = indicateEvaluation;
	}

	
	

}
