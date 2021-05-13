package com.solidarity.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Strand {

	//attributes and relationships
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int strandId;
	private String strandName;
	private String strand_number;
	@ManyToOne
	@JoinColumn(name="learning_area_id", nullable=false)
	private LearningArea learningArea;
	
	@OneToMany(mappedBy="strand", cascade = CascadeType.PERSIST)
	private List<SubStrand> sub_strands;
	
	
	
	//default constructor
	public Strand() {
		super();
		
	}

	
	
	//constructor using fields
	public Strand(int stand_id, String strand_name, String strand_number, LearningArea learning_area,List<SubStrand> sub_strands) {
		super();
		this.strandId = stand_id;
		this.strandName = strand_name;
		this.strand_number = strand_number;
		this.learningArea = learning_area;
		this.sub_strands = sub_strands;
	}





	//getters and setters
	public int getStrandId() {
		return strandId;
	}

	public void setStrandId(int stand_id) {
		this.strandId = stand_id;
	}

	public String getStrandName() {
		return strandName;
	}

	public void setStrandName(String strand_name) {
		this.strandName = strand_name;
	}

	public String getStrand_number() {
		return strand_number;
	}

	public void setStrand_number(String strand_number) {
		this.strand_number = strand_number;
	}

	public LearningArea getLearningArea() {
		return learningArea;
	}

	public void setLearningArea(LearningArea learning_area) {
		this.learningArea = learning_area;
	}



	public List<SubStrand> getSub_strands() {
		return sub_strands;
	}



	public void setSub_strands(List<SubStrand> sub_strands) {
		this.sub_strands = sub_strands;
	}
	
	

	
}
