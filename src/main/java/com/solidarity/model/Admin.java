package com.solidarity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admin_id;
	private String phoneNumber;
	private String password;
	private String first_name;
	private String last_name;

	
	//default constructor
	public Admin() {
		super();

	}
	// constructor using fields
	public Admin(int admin_id, String phone_number, String password, String first_name, String last_name) {
		super();
		this.admin_id = admin_id;
		this.phoneNumber = phone_number;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	//getters and setters
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getPhone_number() {
		return phoneNumber;
	}

	public void setPhone_number(String phone_number) {
		this.phoneNumber = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
