package com.solidarity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Score {

	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int score_id;
	private Integer score_value = 0;
	private boolean scoreStatus;
	
	@ManyToOne
	@JoinColumn(name="learner_id",nullable = false)
	private Learner learner;

	@ManyToOne
	@JoinColumn(name = "sub_strand_id", nullable = false)
	private SubStrand subStrand;

	//default constructor
	public Score() {
		super();

	}

	//constructor using fields
	public Score(int score_id, int score_value, SubStrand sub_strand) {
		super();
		this.score_id = score_id;
		this.score_value = score_value;
		this.subStrand = sub_strand;
	}

	
	//getters and setters
	
	
	public int getScore_id() {
		return score_id;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}

	public int getScore_value() {
		return score_value;
	}

	public void setScore_value(Integer score_value) {
		this.score_value = score_value;
	}
	
	
	

	public boolean isScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(boolean scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public SubStrand getSubStrand() {
		return subStrand;
	}

	public void setSubStrand(SubStrand sub_strand) {
		this.subStrand = sub_strand;
	}

}
