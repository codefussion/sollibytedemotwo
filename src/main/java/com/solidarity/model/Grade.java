package com.solidarity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="grade")
public class Grade {

	// attributes and the relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gradeId;
	private int gradeNumber;
	private String gradeDisplayNumber;
	private String gradeStream;


	@ManyToOne
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	@OneToMany(mappedBy = "grade")
	private List<Learner> learners = new ArrayList<Learner>();

	@OneToOne
	@JoinColumn(name = "class_teacher_id")
	private ClassTeacher class_teacher;

	
	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE ,CascadeType.PERSIST},mappedBy = "grades") 
	private List<SubjectTeacher> subject_teachers;

	@OneToMany(mappedBy = "grade")
	private List<LearningArea> learning_areas;
	
	private Integer finalClassTeacherId;
	private int year;
	private Integer term;
	
	
	@Column(name="termEnded")
    private Boolean termEnded;
	
	//these are just for communication
	private Boolean beenTermOne=false;
	private Boolean beenTermTwo=false;
	private Boolean beenTermThree=false;
	
	
	// default constructor
	public Grade() {
		super();

	}

	// constructor using fields
	public Grade(int grade_id, int gradeNumber, String grade_stream, School school, List<Learner> learners,
			ClassTeacher class_teacher, List<SubjectTeacher> subject_teachers, List<LearningArea> learning_areas) {
		super();
		this.gradeId = grade_id;
		this.gradeNumber = gradeNumber;
		this.gradeStream = grade_stream;
		this.school = school;
		this.learners = learners;
		this.class_teacher = class_teacher;
		this.subject_teachers = subject_teachers;
		this.learning_areas = learning_areas;
	}

	// getters and setters
	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int grade_id) {
		this.gradeId = grade_id;
	}

	public int getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(int gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

	public String getGradeStream() {
		return gradeStream;
	}

	public void setGradeStream(String grade_stream) {
		this.gradeStream = grade_stream;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Learner> getLearners() {
		return learners;
	}

	public void setLearners(List<Learner> learners) {
		this.learners = learners;
	}

	public ClassTeacher getClass_teacher() {
		return class_teacher;
	}

	public void setClass_teacher(ClassTeacher class_teacher) {
		this.class_teacher = class_teacher;
	}

	public List<SubjectTeacher> getSubject_teachers() {
		return subject_teachers;
	}

	public void setSubject_teachers(List<SubjectTeacher> subject_teachers) {
		this.subject_teachers = subject_teachers;
	}

	public List<LearningArea> getLearning_areas() {
		return learning_areas;
	}

	public void setLearning_areas(List<LearningArea> learning_areas) {
		this.learning_areas = learning_areas;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Boolean getTermEnded() {
		return termEnded;
	}

	public void setTermEnded(Boolean termEnded) {
		this.termEnded = termEnded;
	}

	public Boolean isBeenTermThree() {
		return beenTermThree;
	}

	public void setBeenTermThree(Boolean beenTermThree) {
		this.beenTermThree = beenTermThree;
	}

	public Boolean isBeenTermTwo() {
		return beenTermTwo;
	}

	public void setBeenTermTwo(Boolean beenTermTwo) {
		this.beenTermTwo = beenTermTwo;
	}

	public Boolean isBeenTermOne() {
		return beenTermOne;
	}

	public void setBeenTermOne(Boolean beenTermOne) {
		this.beenTermOne = beenTermOne;
	}

	public int getFinalClassTeacherId() {
		return finalClassTeacherId;
	}

	public void setFinalClassTeacherId(int finalClassTeacherId) {
		this.finalClassTeacherId = finalClassTeacherId;
	}

	public String getGradeDisplayNumber() {
		return gradeDisplayNumber;
	}

	public void setGradeDisplayNumber(String gradeDisplayNumber) {
		this.gradeDisplayNumber = gradeDisplayNumber;
	}

}
