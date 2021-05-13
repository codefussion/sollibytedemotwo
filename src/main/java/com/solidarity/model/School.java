package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class School {

	// Attributes of the school
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	private String schoolName;
	private String codeNumber;
	private Boolean paid = false;

	@OneToMany(mappedBy = "school")
	private List<Learner> learners;

	@OneToMany(mappedBy = "school")
	private List<ClassTeacher> class_teachers;

	@OneToMany(mappedBy = "school")
	private List<SubjectTeacher> subject_teachers;

	@OneToMany(mappedBy = "school")
	private List<Grade> grades;

	@OneToOne(mappedBy = "school")
	private HeadTeacher head_teacher;

	@ManyToOne
	@JoinColumn(name = "county_id", nullable = false)
	private County county;
	
	@ManyToOne
	@JoinColumn(name="enroller_id" , nullable = false)
	private Enroller enroller;

	// default constructor
	public School() {
		super();

	}

	// constructor using fields
	public School(int school_id,String schoolName, String codeNumber, List<Learner> learners,
			List<ClassTeacher> class_teachers, List<SubjectTeacher> subject_teachers, HeadTeacher head_teacher,
			List<Grade> grades, County county ,Enroller enroller) {
		super();
		this.schoolId = school_id;
		this.schoolName = schoolName;
		this.codeNumber = codeNumber;
		this.learners = learners;
		this.class_teachers = class_teachers;
		this.subject_teachers = subject_teachers;
		this.head_teacher = head_teacher;
		this.grades = grades;
		this.county = county;
		this.enroller = enroller;
	}

	// getters and setters
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int school_id) {
		this.schoolId = school_id;
	}


	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public List<Learner> getLearners() {
		return learners;
	}

	public void setLearners(List<Learner> learners) {
		this.learners = learners;
	}

	public List<ClassTeacher> getClass_teachers() {
		return class_teachers;
	}

	public void setClass_teachers(List<ClassTeacher> class_teachers) {
		this.class_teachers = class_teachers;
	}

	public List<SubjectTeacher> getSubject_teacher() {
		return subject_teachers;
	}

	public void setSubject_teacher(List<SubjectTeacher> subject_teacher) {
		this.subject_teachers = subject_teacher;
	}

	public HeadTeacher getHead_teacher() {
		return head_teacher;
	}

	public void setHeadTeacher(HeadTeacher head_teacher) {
		this.head_teacher = head_teacher;
	}

	public List<Grade> getGrade() {
		return grades;
	}

	public void setGrade(List<Grade> grades) {
		this.grades = grades;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Enroller getEnroller() {
		return enroller;
	}

	public void setEnroller(Enroller enroller) {
		this.enroller = enroller;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	

}
