package com.solidarity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SubStrand {

	// Attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subStrandId;
	private String subStrandName;
	private String sub_strand_number;

	@ManyToOne
	@JoinColumn(name = "strand_id", nullable = false)
	private Strand strand;

	@OneToMany(mappedBy = "subStrand")
	private List<Score> scores;

	// default constructor
	public SubStrand() {
		super();
	}

	// constructor using fields
	public SubStrand(int sub_strand_id, String sub_strand_name, String sub_strand_number, Strand strand,
			List<Score> scores) {
		super();
		this.subStrandId = sub_strand_id;
		this.subStrandName = sub_strand_name;
		this.sub_strand_number = sub_strand_number;
		this.strand = strand;
		this.scores = scores;
	}

	// getters and setters
	public int getSubStrandId() {
		return subStrandId;
	}

	public void setSubStrandId(int sub_strand_id) {
		this.subStrandId = sub_strand_id;
	}

	public String getSubStrandName() {
		return subStrandName;
	}

	public void setSubStrandName(String sub_strand_name) {
		this.subStrandName = sub_strand_name;
	}

	public String getSub_strand_number() {
		return sub_strand_number;
	}

	public void setSub_strand_number(String sub_strand_number) {
		this.sub_strand_number = sub_strand_number;
	}

	public Strand getStrand() {
		return strand;
	}

	public void setStrand(Strand strand) {
		this.strand = strand;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

}
