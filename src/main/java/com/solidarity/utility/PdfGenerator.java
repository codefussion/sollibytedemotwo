package com.solidarity.utility;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.solidarity.model.Learner;
import com.solidarity.model.LearningArea;

@Component
public class PdfGenerator {

	//variables required
	private Learner learner;
	private List<LearningArea> learningAreas;

	
	//getters and setters
	public List<LearningArea> getLearningAreas() {
		return learningAreas;
	}

	public void setLearningAreas(List<LearningArea> learningAreas) {
		this.learningAreas = learningAreas;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	
	//creating the table header
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding((float) 5.33);

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		cell.setPhrase(new Phrase("Learning Area Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Activity", font));
		table.addCell(cell);
	}

	//creating the table data
	private void writeTableData(PdfPTable table) {
		for (LearningArea learningArea : learningAreas) {
			table.addCell(learningArea.getLearningAreaName());
			
			if(learningArea.getRubric()==null)
			{
				table.addCell("Not available");
			}
			else
			{
				switch (learningArea.getRubric()) {
				case "none":
					table.addCell("Not available");
				break;
				
				case "E.E":
					table.addCell("Exceeding Expectations");
					break;

				case "M.E":
					table.addCell("Meets Expectations");

					break;

				case "A.E":
					table.addCell("Approaches Expectations");
					break;

				case "B.E":
					table.addCell("Below Expectations");
					
			}

		}
			
		
		}

	}
	
	//writing the class teacher remarks
	public void writeClassTeacherRemarks(Learner learner,Document document) throws DocumentException
	{
		
		   Font font = FontFactory.getFont(FontFactory.TIMES);
		   font.setSize(14);
		   font.setColor(255, 0, 0);
		
		switch(learner.getOveralPoint())
		{
		   case 1:   
			   Paragraph paragragh = new Paragraph("Class Teacher's  Remarks : Good work " + learner.getFirstName()+" but you need to strive for Approaches Expectations and Above.",font);
			   document.add(paragragh);
		  break;
		  
		   case 2:
			   Paragraph paragraphTwo = new Paragraph("Class Teacher's  Remarks : Good work " + learner.getFirstName()+" but you need to strive for Meets Expectations and Above.",font);
			   document.add(paragraphTwo);
		   break;
		    
		   
		   case 3:
			   Paragraph paragraphThree = new Paragraph("Class Teacher's  Remarks : Good work " + learner.getFirstName()+" but you need to strive for Exceeds Expectations.",font);
			   document.add(paragraphThree);   
		   break;
		   
		   case 4:
			   Paragraph paragraphFour = new Paragraph("Class Teacher's  Remarks : Good work " + learner.getFirstName()+" congratulations now you need to  maintain it here.",font);
			   document.add(paragraphFour);
		   break;
			   	   
		}
			
	}
	public void writeHeadTeacherRemarks(Learner learner,Document document) throws DocumentException
	{
		
		   Font font = FontFactory.getFont(FontFactory.TIMES);
		   font.setSize(14);
		   font.setColor(0, 0, 255);
		
		switch(learner.getOveralPoint())
		{
		   case 1:   
			   Paragraph paragragh = new Paragraph("Head Teacher's  Remarks : Good work " + learner.getFirstName()+" but you need to strive for better overal rating.",font);
			   document.add(paragragh);
		  break;
		  
		   case 2:
			   Paragraph paragraphTwo = new Paragraph("Head Teacher's  Remarks : Good work " + learner.getFirstName()+" you have the potential to become better.",font);
			   document.add(paragraphTwo);
		   break;
		    
		   
		   case 3:
			   Paragraph paragraphThree = new Paragraph("Head Teacher's  Remarks : Good work " + learner.getFirstName()+" continue with the work to move to greater heights.",font);
			   document.add(paragraphThree);   
		   break;
		   
		   case 4:
			   Paragraph paragraphFour = new Paragraph("Head Teacher's  Remarks : Good work " + learner.getFirstName()+" it is time to defend this title.",font);
			   document.add(paragraphFour);
		   break;
			   	   
		}
			
	}

	//creating the document
	public void export(HttpServletResponse response,int termNumber) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(20);

		String schoolName = learner.getSchool().getSchoolName();
		String gradeDetails = "Grade: "+String.valueOf(learner.getGrade().getGradeNumber()) + "  "
				+ learner.getGrade().getGradeStream() + " " + learner.getGrade().getYear();

		Paragraph paragraph = new Paragraph(schoolName, font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);

		font.setSize(17);
		Paragraph paragraphTwo = new Paragraph("Academic Report", font);
		paragraphTwo.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraphTwo);

		font.setSize(14);
		Paragraph paragraphThree = new Paragraph("Name: "+
				learner.getFirstName() + "  " + learner.getSecondName() + "  " + learner.getSurName(), font);
		document.add(paragraphThree);

		Paragraph paragraphFour = new Paragraph(gradeDetails, font);
		document.add(paragraphFour);

		Paragraph paragraphFive = new Paragraph("Term: " + termNumber,font);
		document.add(paragraphFive);
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);
		
		
		document.add(table);
		
		font.setColor(0, 255, 0);
		font.setSize(12);
		
		switch(learner.getOveralPoint())
		{
		case 1:
			Paragraph paragraphOveral = new Paragraph("Overal Rating: Below Expectations",font);
			document.add(paragraphOveral);
			
		break;
		case 2:
			Paragraph paragraphOveralTwo = new Paragraph("Overal Rating: Approaches Expectations",font);
			document.add(paragraphOveralTwo);
			
		break;
		case 3:
			Paragraph paragraphOveralThree = new Paragraph("Overal Rating: Meets Expectations",font);
			document.add(paragraphOveralThree);
			
		break;
		case 4:
			Paragraph paragraphOveralFour = new Paragraph("Overal Rating: Exeeds Expectations",font);
			document.add(paragraphOveralFour);
			
		break;
		
		
		}
		
		writeClassTeacherRemarks(learner,document);
		writeHeadTeacherRemarks(learner,document);
		
		
		document.close();

	}
}
