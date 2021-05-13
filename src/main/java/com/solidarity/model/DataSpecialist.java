package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DataSpecialist {

	// attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dataSpecialistId;
	private String firstName;
	private String secondName;
	private String lastName;
	private String idNumber;
	private String phoneNumber;
	private Boolean approved;
	private String password;
	
	@OneToMany(mappedBy="dataSpecialist")
	private List<WorkSheet> workSheet;

	

	// getters and setters
	public int getDataSpecialistId() {
		return dataSpecialistId;
	}

	public void setDataSpecialistId(int dataSpecialistId) {
		this.dataSpecialistId = dataSpecialistId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<WorkSheet> getWorkSheet() {
		return workSheet;
	}

	public void setWorkSheet(List<WorkSheet> workSheet) {
		this.workSheet = workSheet;
	}
}
