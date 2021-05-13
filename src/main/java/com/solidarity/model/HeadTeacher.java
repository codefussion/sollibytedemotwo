package com.solidarity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class HeadTeacher {
	// attributes and relationships
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int head_teacher_id;
	private String first_name;
	private String last_name;
	private String phoneNumber;
	private String password;

	@OneToOne
	@JoinColumn(name = "school_id")
	private School school;

	// default constructor
	public HeadTeacher() {
		super();
	}

	//constructor using fields
	public HeadTeacher(int head_teacher_id, String first_name, String last_name, String phoneNumber, String password,
			School school) {
		super();
		this.head_teacher_id = head_teacher_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.school = school;
	}

	//getters and setters
	public int getHead_teacher_id() {
		return head_teacher_id;
	}

	public void setHead_teacher_id(int head_teacher_id) {
		this.head_teacher_id = head_teacher_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
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

}
