package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ClassTeacher {
	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classTeacherId;
    private String first_name;
    private String second_name;
    private String last_name;
    private String phoneNumber;
    private String password;
	
	@ManyToOne
	@JoinColumn(name="school_id", nullable=false)
	private School school;
	
	@OneToMany(mappedBy="class_teacher")
	private List<LearningArea> learning_areas;
	
	@OneToOne(mappedBy="class_teacher",  fetch = FetchType.LAZY)
	private Grade grade;

	//default constructor
	public ClassTeacher() {
		super();
		
	}

	//constructor using fields
	public ClassTeacher(int class_teacher_id, String first_name, String second_name, String last_name,
			String phoneNumber, String password, School school, List<LearningArea> learning_areas, Grade grade) {
		super();
		this.classTeacherId = class_teacher_id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.last_name = last_name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.school = school;
		this.learning_areas = learning_areas;
		this.grade = grade;
	}

	//getters and setters
	public int getClassTeacherId() {
		return classTeacherId;
	}

	public void setClassTeacherId(int class_teacher_id) {
		this.classTeacherId = class_teacher_id;
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

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
	
	
	
	
	
}
