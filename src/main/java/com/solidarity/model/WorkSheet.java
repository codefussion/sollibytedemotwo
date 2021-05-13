package com.solidarity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WorkSheet {
	
	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workSheetId;
	
	@ManyToOne
	@JoinColumn(name="data_specialist_id",nullable=false)
	private DataSpecialist dataSpecialist;
	private int learnerId;
	private int month;
	private int year;
	private String learningAreaName;
	
	//getters and setters
	public int getWorkSheetId() {
		return workSheetId;
	}
	public void setWorkSheetId(int workSheetId) {
		this.workSheetId = workSheetId;
	}
	public DataSpecialist getDataSpecialist() {
		return dataSpecialist;
	}
	public void setDataSpecialist(DataSpecialist dataSpecialist) {
		this.dataSpecialist = dataSpecialist;
	}
	public int getLearnerId() {
		return learnerId;
	}
	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getLearningAreaName() {
		return learningAreaName;
	}
	public void setLearningAreaName(String learningAreaName) {
		this.learningAreaName = learningAreaName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
