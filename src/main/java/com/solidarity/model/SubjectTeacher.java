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
import javax.persistence.Table;

@Entity
@Table(name="subject_teacher")
public class SubjectTeacher {
	
	// the attributes and the relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectTeacherId;
	private String first_name;
	private String second_name;
	private String last_name;
	private String phoneNumber;
	private String password;
	
	@ManyToOne
	@JoinColumn(name="school_id", nullable=false)
	private School school;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "subject_teacher_learning_area", joinColumns = @JoinColumn(name = "subject_teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "learning_area_id"))
	private List<LearningArea> learning_areas;

	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE ,CascadeType.PERSIST}) 
	@JoinTable(name = "subject_teacher_grade", joinColumns = {@JoinColumn(name = "subject_teacher_id")}, 
	  inverseJoinColumns = {@JoinColumn(name = "grade_id")})
	private List<Grade> grades;

	//default constructor
	public SubjectTeacher() {
		super();
		
	}

	//constructor using fields
	public SubjectTeacher(int subject_teacher_id, String first_name, String second_name, String last_name,
			String phone_number, String password, School school, List<LearningArea> learning_areas,
			List<Grade> grades) {
		super();
		this.subjectTeacherId = subject_teacher_id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.last_name = last_name;
		this.phoneNumber = phone_number;
		this.password = password;
		this.school = school;
		this.learning_areas = learning_areas;
		this.grades = grades;
	}

	//getters and setters
	public int getSubjectTeacherId() {
		return subjectTeacherId;
	}

	public void setSubjectTeacherId(int subject_teacher_id) {
		this.subjectTeacherId = subject_teacher_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone_number) {
		this.phoneNumber = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<LearningArea> getLearning_areas() {
		return learning_areas;
	}

	public void setLearning_areas(List<LearningArea> learning_areas) {
		this.learning_areas = learning_areas;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	
	
	
	
	
	
}
