package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class County {

	// attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int county_id;
	private String countyName;

	@OneToMany(mappedBy = "county")
	private List<School> schools;

	// default constructor
	public County() {
		super();

	}

	// constructor using fields
	public County(int county_id, String countyName, List<School> schools) {
		super();
		this.county_id = county_id;
		this.countyName = countyName;
		this.schools = schools;
	}

	// getters and setters
	public int getCounty_id() {
		return county_id;
	}

	public void setCounty_id(int county_id) {
		this.county_id = county_id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

}
