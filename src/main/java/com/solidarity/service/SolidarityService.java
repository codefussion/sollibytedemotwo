package com.solidarity.service;

import java.util.List;

import com.solidarity.model.Admin;
import com.solidarity.model.ClassTeacher;
import com.solidarity.model.County;
import com.solidarity.model.DataSpecialist;
import com.solidarity.model.Enroller;
import com.solidarity.model.Grade;
import com.solidarity.model.HeadTeacher;
import com.solidarity.model.Learner;
import com.solidarity.model.LearningArea;
import com.solidarity.model.ResultRecord;
import com.solidarity.model.School;
import com.solidarity.model.Score;
import com.solidarity.model.Strand;
import com.solidarity.model.SubStrand;
import com.solidarity.model.SubjectTeacher;
import com.solidarity.model.WorkSheet;

public interface SolidarityService {
	
	//saving admin
	public void saveAdmin(Admin admin);
	
	//saving  the enroller
	public void saveEnroller(Enroller enroller);

	//find enroller using phone number
	public Enroller findEnrollerUsingPhoneNumber(String phone_number);
	
	//save School to database
	public void saveSchool(School school);
	
	//find county using phone number
	public County getCountyByName(String countyName);
	
	//find school using codeNumber
	School findUSingCodeNumber(String codeNumber);
	
	//find School using name
	School findSchoolUsingName(String schoolName);
	
	//save HeadTeacher
	public void saveHeadTeacher(HeadTeacher headTeacher);
	
	//find grade using Stream name
	Grade findGradeUsingStream(String gradeStream);
	
	//save grade  to database
	public void saveGrade(Grade grade);
	
	//find Grade using grade Number stream name and schoolId
	Grade findGradeUsingNumber(int gradeNumber, String gradeStream, School school);
	
	//save the  class teacher
	public void saveClassTeacher(ClassTeacher classTeacher);

	//findClassTeacher using phonenumber
	ClassTeacher findClassTeacherUsingPhoneNumber(String phoneNumber);
	
	//save the learner
	public void saveLearner(Learner learner);
	
	//findLearnerUsing nemissNumber
	List<Learner> findLearnerUsingNemissNumber(String nemissNumber);
	
	//find class teacher using  class teacher id
	ClassTeacher findClassTeacherUsingClassTeacherId(int classTeacherId);
	
	//find subject Teacher Using phone Number
	SubjectTeacher findSubjectTeacherUsingPhoneNumber(String phoneNumber);
	
	//save the subject Teacher
	public void saveSubjectTeacher(SubjectTeacher subjectTeacher );
	
	
	//find learning area using grade
	List<LearningArea> findLearningAreaUsingGrade(Grade grade);
	
	//save learning Area
	public void saveLearningArea(LearningArea learningArea);
	
	//find subject teacher using subject teacher id
	SubjectTeacher findUsingSubjectTeacherId(int subjectTeacherId);
	
	//find School using id
	School findSchoolUsingSchoolId(int schoolId);
	
	//find HeadTeacher using phone number
	HeadTeacher findHeadTeacherUsingPhoneNumber(String phoneNumber);

	//find learning area using subject Teachers
	List<LearningArea> findlearningAreaUsingSubjectTeachers(List<SubjectTeacher> subjectTeachers);

	//find Grade using grade Id
	Grade findGradeUsingGradeId(int gradeId);

	//saving the strand
	public void saveStrand(Strand strand);

	//saving the subStrand
	public void saveSubStrand(SubStrand subStrand);

	//find learningArea using id
	 LearningArea findLearningAreaUsingId(int learningAreaId);

	
	//find strandUsing strand Id
	Strand findStrandUsingStrandId(int strandId);

	//find  strandUsingStrandName
	List<Strand> findStrandUsingStrandName(String strandName);

	//find learner using grade id
	List<Learner> findLearnerUsingGradeId(Grade grade);

	//find learner using learner id
	 Learner findLearnerUsingLearnerId(int parseInt);

	 //saving the score
	public void saveScore(Score scoreIn);

	//find subStrand using substrandId
	SubStrand findSubStrandUsingSubStrandId(int parseInt);

	//find score using learnerId
    Score findScoreUsingLearnerIdAndSubStrand(Learner learner,SubStrand subStrand);

    //find scores using substrand
    List<Score> findScoreUsingSubStrandId(SubStrand subStrand);

    //find scores using learner id 
    List<Score>  findScoreUsingLearnerId(Learner learner);

    //find strands using learningArea
	 List<Strand> findStrandUsingLearningArea(LearningArea learningArea);

	 //find learning Area using grade and learningAreaName
	 LearningArea findLearningAreaUsingGradeAndLearningAreaName(Grade grade, String learningAreaName);

	 //find strand using learning Area id and strandName
     Strand findStrandUsingLearningAreaIdAndStrandName(LearningArea learningAreaFromInput, String strandName);

     //find grade using number stream school and year
	 Grade findGradeUsingNumberStreamSchoolYear(int pastGradeNumber, String gradeStream, School school, int year);

	 //saving the result record to the database
	public void saveResultRecord(ResultRecord resultRecord);

	//find Result record using learnerId and learningAreaName
	public ResultRecord findResultRecordUsingLearnerIdAndLearningAreaName(Learner learnerInGrade,
			String learningAreaName);

	//find admin using phoneNumber
	Admin findAdminUsingPhoneNumber(String phoneNumber);

	//find all schools in the database
	List<School> findAllSchoolsEnrolled();
      
	//find all learners in the database
    List<Learner> findAllLearnersEnrolled();

    //find data Specialist using phone number
    DataSpecialist findDataSpecialistUsingPhoneNumber(String phoneNumber);

    //Saving the dataSpecialist
	public void saveDataSpecialist(DataSpecialist dataSpecialist);

	//find all dataSpecialists
	public List<DataSpecialist> findAllDataSpecialists();

	//saving the work sheet to the db
	public void saveWorkSheet(WorkSheet workSheet);

	//deleting subject teacher from db
	public void deleteSubjectTeacher(SubjectTeacher subjectTeacher);

	//find all enrollers
	public List<Enroller> findAllEnrollers();

	//saving the county
	public void saveCounty(County county);
	
	

}