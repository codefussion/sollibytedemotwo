package com.solidarity.utility;

import org.springframework.stereotype.Component;

import com.solidarity.model.Grade;

@Component
public class GenerateGradeDisplayNameImpl implements GenerateGradeDisplayName {

	
	//creating the grade display name
	@Override
	public Grade createDisplayName(Grade grade) {
		
		int gradeNumber = grade.getGradeNumber();
		
		switch(gradeNumber)
		{
		
	      case 1:
	    	  
	    	  grade.setGradeDisplayNumber("PP-1");
	    	   
	      break; 
	      case 2:
	    	  grade.setGradeDisplayNumber("PP-2");
	    	  
	      break;
	      case 3:
	    	  grade.setGradeDisplayNumber("1");
	    	  
		  break;
		  
	      case 4:
	    	  grade.setGradeDisplayNumber("2");
	    	  
		  break;
		  
	      case 5:
	    	  grade.setGradeDisplayNumber("3");
	    	  
		      break;
	      case 6:
	    	  grade.setGradeDisplayNumber("4");
	    	  
		      break;
	      case 7:
	    	  grade.setGradeDisplayNumber("5");
	    	  
		  break;
	      case 8:
	    	  grade.setGradeDisplayNumber("6");
	    	  
		  break;
	      case 9:
	    	  grade.setGradeDisplayNumber("7");
	    	  
		 break;
	      case 10:
	    	  grade.setGradeDisplayNumber("8");
	    	  
	 	 break;
	      case 11:
	    	  grade.setGradeDisplayNumber("9");
	    	  
	 	 break;
	      case 12:
	    	  grade.setGradeDisplayNumber("10");
	    	  
	 	 break;
		
		}
		return grade;
	}

}
