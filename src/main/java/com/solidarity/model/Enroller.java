package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Enroller {
	
	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enroller_id;
	private String first_name;
	private String last_name;
	private String phoneNumber;
	private String password;
	private String IdNumber;
	


	@OneToMany(mappedBy = "enroller")
	private List<School> schools;
	
	//default constructor
	public Enroller() {
		super();
		
	}

	//constructor using fields
	public Enroller(int enroller_id, String first_name, String last_name, String phoneNumber, String password,
			List<School> schools) {
		super();
		this.enroller_id = enroller_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.schools = schools;
	}

	
	//getters and setters
	public int getEnroller_id() {
		return enroller_id;
	}

	public void setEnroller_id(int enroller_id) {
		this.enroller_id = enroller_id;
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

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public String getIdNumber() {
		return IdNumber;
	}

	public void setIdNumber(String idNumber) {
		IdNumber = idNumber;
	}


}
