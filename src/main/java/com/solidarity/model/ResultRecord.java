package com.solidarity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ResultRecord {
	
	//The attributes and the relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultRecordId;
	
	@ManyToOne
	@JoinColumn(name="learner_id",nullable=false)
	private Learner  learner;
	private String learningAreaName;
	private Integer overalTermOneScore;
	private Integer overalTermTwoScore;
	private Integer overalTermThreeScore;
	
	
	//The getters and setters
	public int getResultRecordId() {
		return resultRecordId;
	}
	public void setResultRecordId(int resultRecordId) {
		this.resultRecordId = resultRecordId;
	}

	public String getLearningAreaName() {
		return learningAreaName;
	}
	public void setLearningAreaName(String learningAreaName) {
		this.learningAreaName = learningAreaName;
	}
	public Integer getOveralTermOneScore() {
		return overalTermOneScore;
	}
	public void setOveralTermOneScore(Integer overalTermOneScore) {
		this.overalTermOneScore = overalTermOneScore;
	}
	public Integer getOveralTermTwoScore() {
		return overalTermTwoScore;
	}
	public void setOveralTermTwoScore(Integer overalTermTwoScore) {
		this.overalTermTwoScore = overalTermTwoScore;
	}
	public Learner getLearner() {
		return learner;
	}
	public void setLearner(Learner learner) {
		this.learner = learner;
	}
	public Integer getOveralTermThreeScore() {
		return overalTermThreeScore;
	}
	public void setOveralTermThreeScore(Integer overalTermThreeScore) {
		this.overalTermThreeScore = overalTermThreeScore;
	}
	
}
