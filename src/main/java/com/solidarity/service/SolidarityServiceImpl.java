package com.solidarity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.solidarity.repository.AdminRepository;
import com.solidarity.repository.ClassTeacherRepository;
import com.solidarity.repository.CountyRepository;
import com.solidarity.repository.DataSpecialistRepository;
import com.solidarity.repository.EnrollerRepository;
import com.solidarity.repository.GradeRepository;
import com.solidarity.repository.HeadTeacherRepository;
import com.solidarity.repository.LearnerRepository;
import com.solidarity.repository.LearningAreaRepository;
import com.solidarity.repository.ResultRecordRepository;
import com.solidarity.repository.SchoolRepository;
import com.solidarity.repository.ScoreRepository;
import com.solidarity.repository.StrandRepository;
import com.solidarity.repository.SubStrandRepository;
import com.solidarity.repository.SubjectTeacherRepository;
import com.solidarity.repository.WorkSheetRepository;

@Service
public class SolidarityServiceImpl implements SolidarityService{
	
	//injecting the repository interfaces
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EnrollerRepository enrollerRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	CountyRepository countyRepository;
	
	@Autowired
	HeadTeacherRepository headTeacherRepository;
	
	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	ClassTeacherRepository classTeacherRepository;
	
	@Autowired
	LearnerRepository learnerRepository;
	
	@Autowired
	SubjectTeacherRepository subjectTeacherRepository;
	
	@Autowired
	LearningAreaRepository learningAreaRepository;
	
	@Autowired
	StrandRepository strandRepository;
	
	@Autowired
	SubStrandRepository subStrandRepository;
	
	@Autowired
	ScoreRepository   scoreRepository;
	
	@Autowired
	ResultRecordRepository resultRecordRepository;
	
	@Autowired
	DataSpecialistRepository dataSpecialistRepository;
	
	@Autowired
	WorkSheetRepository workSheetRepository;
	
	
	
	
	
	//saving enroller to database
	@Override
	public void saveEnroller(Enroller enroller) {
		
		enrollerRepository.save(enroller);
	}

	
	//finding enroller using phone number
	@Override
	public Enroller findEnrollerUsingPhoneNumber(String phoneNumber) {
		
		Enroller enroller = enrollerRepository.findByPhoneNumber(phoneNumber);
		
		return enroller;
	}


	//save the school to database
	@Override
	public void saveSchool(School school) {
		schoolRepository.save(school);	
	}


	//find county using name
	@Override
	public County getCountyByName(String countyName) {
		
		County county = countyRepository.findByCountyNameIgnoreCase(countyName);
		
		return county;
	}


	//find school using code number
	@Override
	public School findUSingCodeNumber(String codeNumber) {
		
		School school = schoolRepository.findByCodeNumber(codeNumber);
		
		return school;
	}


	//find school using school name
	@Override
	public School findSchoolUsingName(String schoolName) {
		School school = schoolRepository.findBySchoolName(schoolName);
		return school;
	}
 
	//save headteacher to database
	public void saveHeadTeacher(HeadTeacher headTeacher)
	{
		headTeacherRepository.save(headTeacher);
	}


	//find grade using Stream name
	@Override
	public Grade findGradeUsingStream(String gradeStream) {
		
	    Grade grade = gradeRepository.findByGradeStreamIgnoreCase(gradeStream);
		return grade;
	}


	//save the grade
	@Override
	public void saveGrade(Grade grade) {
		
		gradeRepository.save(grade);
		
	}


	//finding grade using Number stream and school Id
	@Override
	public Grade findGradeUsingNumber(int gradeNumber,String gradeStream , School school) {
		
		Grade grade = gradeRepository.findGradeByGradeNumberAndGradeStreamAndSchool(gradeNumber, gradeStream, school);
		
		return grade;
	}

	

	//saving the class Teacher to database
	@Override
	public void saveClassTeacher(ClassTeacher classTeacher) {
         
		
		  classTeacherRepository.save(classTeacher);
		
	}


	//find class Teacher using phone number
	@Override
	public ClassTeacher findClassTeacherUsingPhoneNumber(String phoneNumber) {
		
       ClassTeacher classTeacher = classTeacherRepository.findByPhoneNumber(phoneNumber);
		
		return classTeacher;
	}


	//find Learner using nemiss Number
	@Override
	public List<Learner> findLearnerUsingNemissNumber(String nemissNumber) {
		List<Learner> learner = learnerRepository.findByNemissNumber(nemissNumber);
		
		return learner;
	}


	
	//saving the learner to database
	@Override
	public void saveLearner(Learner learner) {
		
		learnerRepository.save(learner);
		
	}


	//finding ClassTeacherUsingId
	@Override
	public ClassTeacher findClassTeacherUsingClassTeacherId(int classTeacherId) {
		
		ClassTeacher classTeacher = classTeacherRepository.findByClassTeacherId(classTeacherId);
		
		return classTeacher;
	}

	//find subject Teacher Using phone Number
	@Override
	public SubjectTeacher findSubjectTeacherUsingPhoneNumber(String phoneNumber) {
		
		SubjectTeacher subjectTeacher = subjectTeacherRepository.findByPhoneNumber(phoneNumber);
		
		return subjectTeacher;
	}

	
	//save the subject Teacher
	@Override
	public void saveSubjectTeacher(SubjectTeacher subjectTeacher) {
		subjectTeacherRepository.save(subjectTeacher);
		
	}


	//find learning area using grade
	@Override
	public List<LearningArea> findLearningAreaUsingGrade(Grade grade) {
		
		List<LearningArea> learningAreas = learningAreaRepository.findByGrade(grade);
		
		return learningAreas;
	}

	//save learning Area
	public void saveLearningArea(LearningArea learningArea)
	{
		 learningAreaRepository.save(learningArea);
	}


	//find subject teacher using id
	@Override
	public SubjectTeacher findUsingSubjectTeacherId(int subjectTeacherId) {
		
	 SubjectTeacher subjectTeacher = subjectTeacherRepository.findBySubjectTeacherId(subjectTeacherId);
		
		return subjectTeacher;
	}


	//find School using id
	@Override
	public School findSchoolUsingSchoolId(int schoolId) {
		
		School school = schoolRepository.findBySchoolId(schoolId);
		
		return school;
	}


	//find HeadTeacher using phone number
	@Override
	public HeadTeacher findHeadTeacherUsingPhoneNumber(String phoneNumber) {
		
		HeadTeacher headTeacher = headTeacherRepository.findByPhoneNumber(phoneNumber);
		
		return headTeacher;
	}


	//find learning area using subjectTeacher
	@Override
	public List<LearningArea> findlearningAreaUsingSubjectTeachers(List<SubjectTeacher> subjectTeachers) {
	
		List<LearningArea> learningAreas = learningAreaRepository.findBySubjectTeachersIn(subjectTeachers);
		
		
		return learningAreas;
	}


	//finding the grade using Grade Id
	@Override
	public Grade findGradeUsingGradeId(int gradeId) {
		
		Grade grade = gradeRepository.findByGradeId(gradeId);
		
		return grade;
	}


	//saving the Strand to the database
	@Override
	public void saveStrand(Strand strand) {
		
		strandRepository.save(strand);
		
	}


	//saving the subStrand to the database
	@Override
	public void saveSubStrand(SubStrand subStrand) {
		
		subStrandRepository.save(subStrand);
		
	}


	//find learning Area using learning area id
	@Override
	public LearningArea findLearningAreaUsingId(int learningAreaId) {
		
		LearningArea learningArea = learningAreaRepository.findByLearningAreaId(learningAreaId);
		
		return learningArea;
	}


	//find strand using strand id
	@Override
	public Strand findStrandUsingStrandId(int strandId) {
		Strand  strand = strandRepository.findByStrandId(strandId);
		return strand;
	}


	//find strand using strand name
	@Override
	public List<Strand> findStrandUsingStrandName(String strandName) {
		
		List<Strand> strands = strandRepository.findByStrandName(strandName);
		
		return strands;
	}


	//find learner using grade  id
	@Override
	public List<Learner> findLearnerUsingGradeId(Grade grade) {
	     
		
		List<Learner> learners  = learnerRepository.findByGrade(grade);
		
		return learners;
	}


	//find learner using learner id
	@Override
	public Learner findLearnerUsingLearnerId(int parseInt) {
		Learner learner = learnerRepository.findByLearnerId(parseInt);
		return learner;
	}


	 //saving the score
	@Override
	public void saveScore(Score scoreIn) {
		 
		scoreRepository.save(scoreIn);
	}


	//find subStrand using substrandId
	@Override
	public SubStrand findSubStrandUsingSubStrandId(int parseInt) {
		SubStrand subStrand= subStrandRepository.findBySubStrandId(parseInt);
		return subStrand;
	}


	//find score using the learner id
	@Override
	public Score findScoreUsingLearnerIdAndSubStrand(Learner learner,SubStrand subStrand) {
		Score score = scoreRepository.findByLearnerAndSubStrand(learner,subStrand);
		return score;
	}


	//find scores using subStrand
	@Override
	public List<Score> findScoreUsingSubStrandId(SubStrand subStrand) {
		List<Score> scores = scoreRepository.findBySubStrand(subStrand);
		return scores;
	}


	//find score using learner id 
	@Override
	public List<Score>  findScoreUsingLearnerId(Learner learner) {
		List<Score>  score = scoreRepository.findByLearner(learner);
		return score;
	}


	//find Strands using learningArea
	@Override
	public List<Strand> findStrandUsingLearningArea(LearningArea learningArea) {
		List<Strand> strands = strandRepository.findByLearningArea(learningArea);
		return strands;
	}


	//find learningArea using grade and learning Area name
	@Override
	public LearningArea findLearningAreaUsingGradeAndLearningAreaName(Grade grade, String learningAreaName) {
		
		LearningArea learningArea = learningAreaRepository.findByGradeAndLearningAreaName(grade,learningAreaName);
		
		return learningArea;
	}


	 //find strand using learning Area id and strandName
	@Override
	public Strand findStrandUsingLearningAreaIdAndStrandName(LearningArea learningAreaFromInput, String strandName) {
		
		Strand strand = strandRepository.findByLearningAreaAndStrandNameIgnoreCase(learningAreaFromInput,strandName);
		
		
		return strand;
	}


	//find grade using number gradeStream school and year
	@Override
	public Grade findGradeUsingNumberStreamSchoolYear(int pastGradeNumber, String gradeStream, School school,
			int year) {
	
		  Grade grade = gradeRepository.findGradeByGradeNumberAndGradeStreamAndSchoolAndYear(pastGradeNumber, gradeStream, school, year);
		return grade;
	}


	//save result record  
	@Override
	public void saveResultRecord(ResultRecord resultRecord) {
		
		resultRecordRepository.save(resultRecord);
	}


	//find Result record using learnerId and learningAreaName
	@Override
	public ResultRecord findResultRecordUsingLearnerIdAndLearningAreaName(Learner learnerInGrade,
			String learningAreaName) {
		
		ResultRecord resultRecord = resultRecordRepository.findByLearnerAndLearningAreaName(learnerInGrade,learningAreaName);
		
		return resultRecord;
	}


	//saving the admin to the database
	@Override
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
		
	}


	//find admin using phone Number
	@Override
	public Admin findAdminUsingPhoneNumber(String phoneNumber) {
		Admin admin = adminRepository.findByPhoneNumber(phoneNumber);
		return admin;
	}


	//find all schools in the database
	@Override
	public List<School> findAllSchoolsEnrolled() {
		List<School> schools =schoolRepository.findAll();
		return schools;
	}


	//find all learners in the database
	@Override
	public List<Learner> findAllLearnersEnrolled() {
     
		List<Learner> learners = learnerRepository.findAll();
		
		return learners;
	}


	//find  data specialist using phone number
	@Override
	public DataSpecialist findDataSpecialistUsingPhoneNumber(String phoneNumber) {
		
		DataSpecialist dataSpecialist = dataSpecialistRepository.findByPhoneNumber(phoneNumber);
		
		return dataSpecialist;
	}


	//saving the data specialist to the database
	@Override
	public void saveDataSpecialist(DataSpecialist dataSpecialist) {
	
		dataSpecialistRepository.save(dataSpecialist);
	}


	//finding all the specialists in dp
	@Override
	public List<DataSpecialist> findAllDataSpecialists() {
		
		
		List<DataSpecialist> dataSpecialists = dataSpecialistRepository.findAll();
		
		return dataSpecialists;
	}


	//saving the work sheet to the db
	@Override
	public void saveWorkSheet(WorkSheet workSheet) {
		
		workSheetRepository.save(workSheet); 
		
	}


	//deleting the subject teacher form the db
	@Override
	public void deleteSubjectTeacher(SubjectTeacher subjectTeacher) {
		subjectTeacherRepository.delete(subjectTeacher);
		
	}


	//finding all enrollers 
	@Override
	public List<Enroller> findAllEnrollers() {
		
		List<Enroller>  enrollers =  enrollerRepository.findAll(); 
		
		return enrollers;
	}


	//saving the county
	@Override
	public void saveCounty(County county) {
		
		countyRepository.save(county);
		
	}





	
	
	
	
	
 

	

	
	
}
