package com.solidarity.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;
import com.solidarity.model.Admin;
import com.solidarity.model.ClassTeacher;
import com.solidarity.model.County;
import com.solidarity.model.Data;
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
import com.solidarity.service.SolidarityService;
import com.solidarity.utility.CalculateResults;
import com.solidarity.utility.GenerateBarGraph;
import com.solidarity.utility.GenerateGradeDisplayName;
import com.solidarity.utility.PdfGenerator;

@Controller
public class SolidarityController {

	@Autowired
	SolidarityService solidarityService;

	@Autowired
	GenerateBarGraph generateBarGraph;

	@Autowired
	ServletContext servletContext;

	@Autowired
	CalculateResults calculateResults;
	
	@Autowired
	GenerateGradeDisplayName generateGradeDisplayName;

	// sends the index page to the browser
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	// logout from portal
	@RequestMapping("logout")
	public String logout() {
		return "index";
	}

	// send the classTeacherLoginForm to the browser
	@RequestMapping("/classTeacherLogin")
	public String showClassTeacherLoginForm() {
		return "classTeacherLoginForm";
	}

	@RequestMapping("/verifyClassTeacher")
	public String verifyClassTeacher(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {
		if (solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber) == null) {
			modelMap.addAttribute("msgOne", "Phone number not available please sign Up !");
			return "classTeacherLoginForm";
		}

		else if (solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber).getPassword().equals(password)) {

			ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);

			String classTeacherPhoneNumber = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber)
					.getPhoneNumber();

			Cookie cookie = new Cookie("classTeacherPhoneNumber", classTeacherPhoneNumber);
			response.addCookie(cookie);

			Grade grade = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber).getGrade();

			List<Learner> learners = solidarityService.findLearnerUsingGradeId(grade);

			modelMap.addAttribute("learnerNumber", learners.size());
			modelMap.addAttribute("learners", learners);
			modelMap.addAttribute("grade", grade);
			modelMap.addAttribute("term", grade.getTerm());
			modelMap.addAttribute("classTeacher", classTeacher);

			return "classTeacherFirstPage";
		}

		else {
			modelMap.addAttribute("msgTwo", "Invalid password please try again");
			return "classTeacherLoginForm";
		}

	}

	// duplicate method for back functionality for the classTeacher
	@RequestMapping("/verifyClassTeacherDuplicate")
	public String verifyClassTeacherDuplicate(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String phoneNumber, ModelMap modelMap,
			HttpServletResponse response) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);

		String classTeacherPhoneNumber = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber)
				.getPhoneNumber();

		Cookie cookie = new Cookie("classTeacherPhoneNumber", classTeacherPhoneNumber);
		response.addCookie(cookie);

		Grade grade = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber).getGrade();

		List<Learner> learners = solidarityService.findLearnerUsingGradeId(grade);

		modelMap.addAttribute("learnerNumber", learners.size());
		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);
		modelMap.addAttribute("term", grade.getTerm());
		modelMap.addAttribute("classTeacher", classTeacher);

		return "classTeacherFirstPage";
	}

	// send enrollerRegisterForm to browser
	@RequestMapping("/registerEnroller")
	public String registerEnroller() {
		return "enrollerRegistrationForm";
	}

	// saving enroller
	@RequestMapping(value = "/saveEnroller", method = RequestMethod.POST)
	public String saveEnroller(@ModelAttribute("enroller") Enroller enroller,ModelMap modelMap) {
		
		Enroller enrollerDb = solidarityService.findEnrollerUsingPhoneNumber(enroller.getPhoneNumber());
		if(enrollerDb==null)
		{
			solidarityService.saveEnroller(enroller);
			return "enrollerLoginForm";
		}
		
		else
		{
			
			modelMap.addAttribute("msg", "Enroller has already been registered!");
			return "enrollerRegistrationForm";
		}

		
	}

	// sending enrollerLoginForm to browser
	@RequestMapping("/enrollerLogin")
	public String showEnrollerLoginForm() {
		return "enrollerLoginForm";
	}

	// verify enroller
	@RequestMapping("/verifyEnroller")
	public String verifyEnroller(@RequestParam("phone_number") String phone_number,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {
		if (solidarityService.findEnrollerUsingPhoneNumber(phone_number) == null) {
			modelMap.addAttribute("msg", "invalid phone number please register");
			return "enrollerLoginForm";
		}

		else if (solidarityService.findEnrollerUsingPhoneNumber(phone_number).getPassword().equals(password)) {

			Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(phone_number);
			Cookie cookie = new Cookie("enrollerPhoneNumber", phone_number);
			response.addCookie(cookie);
			List<School> schools = enroller.getSchools();

			modelMap.addAttribute("enroller", enroller);
			modelMap.addAttribute("schools", schools);

			int schoolNumber = schools.size();
			modelMap.addAttribute("schoolNumber", schoolNumber);

			return "enrollerFirstPage";
		}

		else {
			modelMap.addAttribute("msg", "Invalid username or password please try again!");
		}
		return "enrollerLoginForm";
	}

	// verify enroller duplicate
	@RequestMapping("/verifyEnrollerDuplicate")
	public String verifyEnrollerDuplicate(
			@CookieValue(value = "enrollerPhoneNumber", defaultValue = "00") String phone_number, ModelMap modelMap) {

		Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(phone_number);

		List<School> schools = enroller.getSchools();

		modelMap.addAttribute("enroller", enroller);
		modelMap.addAttribute("schools", schools);

		int schoolNumber = schools.size();
		modelMap.addAttribute("schoolNumber", schoolNumber);

		return "enrollerFirstPage";

	}

	// controller method for redirecting to either headteacher or enroller for the
	// back navigation
	@RequestMapping("/controlVerification")
	public String controllerMethod(
			@CookieValue(value = "enrollerPhoneNumber", defaultValue = "00") String enrollerPhoneNumber,
			@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "00") String headTeacherPhoneNumber) {
		
		System.out.println("Head teacher :" + headTeacherPhoneNumber);
		System.out.println("Enroller:" + enrollerPhoneNumber);
		if (enrollerPhoneNumber.equals("00")) {
			return "redirect:/verifyHeadTeacherDuplicate";
		} else if (headTeacherPhoneNumber.equals("00")) {
			return "redirect:/verifyEnrollerDuplicate";
		} else {
			return "redirect:/logout";
		}
	}

	// send schoolEnrollForm to the browser
	@RequestMapping("/enrollSchool")
	public String showSchoolEnrollForm() {
		return "enrollSchoolForm";
	}

	// saving the school
	@RequestMapping(value = "/saveSchool")
	public String saveSchool(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("countyName") String countyName, @ModelAttribute("school") School school, ModelMap modelMap) {
		if (solidarityService.findEnrollerUsingPhoneNumber(phoneNumber) == null) {
			modelMap.addAttribute("msg", "Invalid phone number");
			return "enrollSchoolForm";
		}

		else if (solidarityService.findEnrollerUsingPhoneNumber(phoneNumber).getPhoneNumber().equals(phoneNumber)) {
			
			School schoolFromDb = solidarityService.findUSingCodeNumber(school.getCodeNumber());
			
			if (schoolFromDb == null)
			{
				Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(phoneNumber);
				County county = solidarityService.getCountyByName(countyName);
				if(county==null)
				{
					County newCounty = new County();
					newCounty.setCountyName(countyName.toUpperCase());
					solidarityService.saveCounty(newCounty);
					school.setCounty(newCounty);
					school.setEnroller(enroller);
					solidarityService.saveSchool(school);
				}
				else
				{
					school.setCounty(county);
					school.setEnroller(enroller);
					solidarityService.saveSchool(school);
				}

				
				return "enrollHeadTeacherForm";
			}
			else
			{
				modelMap.addAttribute("msg", "School has already been enrolled!");
				return "enrollSchoolForm";
			}
			

		}

		else {

			modelMap.addAttribute("msg", "please enter the correct phone number");
		}
		return "enrollSchoolForm";
	}

	// send head teacher enroll form to the browser
	@RequestMapping(value = "/enrollHeadTeacher")
	public String enrollHeadTeacher() {
		return "enrollHeadTeacherForm";
	}

	// save the head teacher
	@RequestMapping("/saveHeadTeacher")
	public String saveHeadTeacher(@ModelAttribute("headTeacher") HeadTeacher headTeacher,
			@RequestParam("schoolName") String schoolName, @RequestParam("codeNumber") String codeNumber,
			ModelMap modelMap) {
		if (solidarityService.findUSingCodeNumber(codeNumber) == null) {
			modelMap.addAttribute("msg", "Invalid school code number");
			return "enrollHeadTeacherForm";
		}

		else if (solidarityService.findUSingCodeNumber(codeNumber).getCodeNumber()
				.equals(solidarityService.findSchoolUsingName(schoolName).getCodeNumber())) {
			
			HeadTeacher headTeacherDb = solidarityService.findHeadTeacherUsingPhoneNumber(headTeacher.getPhoneNumber());
			if(headTeacherDb==null)
			{
				School school = solidarityService.findUSingCodeNumber(codeNumber);

				headTeacher.setSchool(school);

				solidarityService.saveHeadTeacher(headTeacher);
				
				return "successPage";
			}
			else
			{
				modelMap.addAttribute("msg", "Head teacher already enrolled please try again!");
				return "enrollHeadTeacherForm";
			}

			
		}

		else {
			modelMap.addAttribute("msg", "Wrong code number");
		}
		return "enrollHeadTeacherForm";
	}

	// enroll grade
	@RequestMapping(value = "/enrollGrade")
	public String enrollGrade() {
		return "enrollGradeForm";
	}

	// save the grade
	@RequestMapping("/saveGrade")
	public String saveGrade(@RequestParam("schoolCode") String schoolCode,
			@RequestParam("gradeStream") String gradeStream, @RequestParam("gradeNumber") String gradeNumber,
			@ModelAttribute("grade") Grade grade, ModelMap modelMap, HttpServletResponse response) {
		School school = new School();
		// if school does not exist deny
		if (solidarityService.findUSingCodeNumber(schoolCode) == null) {
			modelMap.addAttribute("msgOne", "Invalid school code please enroll school first!");

			return "enrollGradeForm";
		}

		// if school exists and grade exist deny else accept

		else if (solidarityService.findUSingCodeNumber(schoolCode).getCodeNumber().equals(schoolCode)) {

			int gradeNumberInt = Integer.parseInt(gradeNumber);

			school = solidarityService.findUSingCodeNumber(schoolCode);

			if (solidarityService.findGradeUsingNumber(gradeNumberInt, gradeStream, school) == null) {
				school = solidarityService.findUSingCodeNumber(schoolCode);
				grade.setSchool(school);
				grade.setTerm(1);
				grade.setGradeStream(gradeStream.toUpperCase());
				grade.setTermEnded(false);
				
				
				Grade gradeGenerator = generateGradeDisplayName.createDisplayName(grade);

				solidarityService.saveGrade(gradeGenerator);

				String gradeIdString = String.valueOf(grade.getGradeId());

				Cookie cookie = new Cookie("gradeIdString", gradeIdString);

				response.addCookie(cookie);

				return "enrollClassTeacherForm";

			} else if (solidarityService.findGradeUsingNumber(gradeNumberInt, gradeStream, school).getGradeStream()
					.equals(gradeStream)) {
				modelMap.addAttribute("msgTwo", "grade already available!");
				return "enrollGradeForm";
			}

		}

		else {

			modelMap.addAttribute("msg", "Invalid details please try again!");

		}

		return "enrollGradeForm";
	}

	// send enroll form to browser
	@RequestMapping("/enrollClassTeacher")
	public String enrollClassTeacher() {
		return "enrollClassTeacherForm";
	}

	// saving the class Teacher to the database
	@RequestMapping("/saveClassTeacher")
	public String saveClassTeacher(@ModelAttribute("classTeacher") ClassTeacher classTeacher,
			@RequestParam("schoolCode") String schoolCode, @RequestParam("gradeNumber") String gradeNumber,
			@RequestParam("streamName") String streamName, @RequestParam("year") int year, ModelMap modelMap) {

		if (solidarityService.findUSingCodeNumber(schoolCode) == null) {

			modelMap.addAttribute("msgOne", "Invalid school code please register school!");

			return "enrollClassTeacherForm";
		}

		else if (solidarityService.findUSingCodeNumber(schoolCode).getCodeNumber().equals(schoolCode)) {
			School school = solidarityService.findUSingCodeNumber(schoolCode);
			int gradeNumberInt = Integer.parseInt(gradeNumber);

			if (solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumberInt, streamName.toUpperCase(), school,
					year) == null) {
				modelMap.addAttribute("msgTwo", "Grade does not exist please enroll!");
				return "enrollClassTeacherForm";
			}

			else if (solidarityService
					.findGradeUsingNumberStreamSchoolYear(gradeNumberInt, streamName.toUpperCase(), school, year)
					.getGradeStream().equals(streamName.toUpperCase())
					&& solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumberInt, streamName.toUpperCase(),
							school, year).getClass_teacher() == null) {
				Grade grade = solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumberInt,
						streamName.toUpperCase(), school, year);
				grade.setClass_teacher(classTeacher);
				classTeacher.setSchool(school);
				classTeacher.setGrade(grade);

				solidarityService.saveClassTeacher(classTeacher);

				return "classTeacherLoginForm";

			}

			else if (solidarityService
					.findGradeUsingNumberStreamSchoolYear(gradeNumberInt, streamName.toUpperCase(), school, year)
					.getGradeStream().equals(streamName)
					&& solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumberInt, streamName.toUpperCase(),
							school, year).getClass_teacher().equals(classTeacher)) {
				modelMap.addAttribute("msgThree", "Class teacher already enrolled!");

				return "enrollClassTeacherForm";
			}

		}

		modelMap.addAttribute("msgThree", "Class teacher already enrolled!");
		return "enrollClassTeacherForm";
	}

	// sending the enroll learner form to the browser
	@RequestMapping("/enrollLearner")
	public String enrollLearner() {
		return "enrollLearnerForm";
	}

	// save the learner
	@RequestMapping("/saveLearner")
	public String saveLearner(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String classTeacherPhoneNumber,
			@ModelAttribute("learner") Learner learner, @RequestParam("nemissNumber") String nemissNumber,
			ModelMap modelMap) {

		List<Learner> learners = solidarityService.findLearnerUsingNemissNumber(nemissNumber);
		if (learners.isEmpty() == true) {

			School school = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber).getSchool();
			Grade grade = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber).getGrade();

			learner.setSchool(school);
			learner.setGrade(grade);
			solidarityService.saveLearner(learner);
			modelMap.addAttribute("msg", "Learner enrolled successfully!");

			return "enrollLearnerForm";
			

		} else {
			modelMap.addAttribute("msg", "Learner already enrolled!");

			return "enrollLearnerForm";
		}
	}

	// save Subject Teacher
	@RequestMapping("/enrollSubjectTeacher")
	public String enrollSubjectTeacher() {
		return "enrollSubjectTeacherForm";
	}

	@RequestMapping("/saveSubjectTeacher")
	public String saveSubjectTeacher(@ModelAttribute("subjectTeacher") SubjectTeacher subjectTeacher,
			@RequestParam("schoolCode") String schoolCode, @RequestParam("phoneNumber") String phoneNumber,
			ModelMap modelMap) {
		if (solidarityService.findUSingCodeNumber(schoolCode) == null) {
			modelMap.addAttribute("msgOne", "Invalid school code please enroll school!");

			return "enrollSubjectTeacherForm";

		}

		else if (solidarityService.findUSingCodeNumber(schoolCode).getCodeNumber().equals(schoolCode)) {

			if (solidarityService.findSubjectTeacherUsingPhoneNumber(phoneNumber) == null) {
				subjectTeacher.setSchool(solidarityService.findUSingCodeNumber(schoolCode));

				solidarityService.saveSubjectTeacher(subjectTeacher);

				return "successPage";
			}

			else if (solidarityService.findSubjectTeacherUsingPhoneNumber(phoneNumber).getPhoneNumber()
					.equals(phoneNumber)) {
				modelMap.addAttribute("msgTwo", "SubjectTeacher already enrolled!");
				return "enrollSubjectTeacher";
			}

		}

		else {

			modelMap.addAttribute("msgTwo", "Invalid credentials please Try again!");
		}

		return "subjectTeacherFirstPage";
	}

	// send the enroll Learning Area form to the browser
	@RequestMapping("/enrollLearningArea")
	public String enrollLearningArea() {
		return "enrollLearningAreaForm";
	}

	// save the learning area
	@RequestMapping("/saveLearningArea")
	public String saveLearningArea(@ModelAttribute("learningArea") LearningArea learningArea,
			@RequestParam("learning_area_name") String learning_area_name,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String classTeacherPhoneNumber,
			ModelMap modelMap) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();

		List<LearningArea> learningAreas = solidarityService.findLearningAreaUsingGrade(grade);

		if (learningAreas.isEmpty() == true) {
			learningArea.setGrade(grade);
			learningArea.setLearningAreaName(learning_area_name.toUpperCase());
			solidarityService.saveLearningArea(learningArea);
			
			modelMap.addAttribute("msgOne", "Learning area registered successfully !");
			
      
			return "enrollLearningAreaForm";
		}

		else if (learningAreas.isEmpty() == false) {

			List<LearningArea> learningAreasTwo = new ArrayList<LearningArea>();

			for (int i = 0; i < learningAreas.size(); i++) {

				LearningArea learningAreaOne = learningAreas.get(i);

				if (!learningAreaOne.getLearningAreaName().toUpperCase().equals(learning_area_name.toUpperCase())) {

					learningAreasTwo.add(learningAreaOne);

				}
			}

			if (learningAreasTwo.equals(learningAreas)) {
				learningArea.setGrade(grade);
				learningArea.setLearningAreaName(learning_area_name.toUpperCase());
				solidarityService.saveLearningArea(learningArea);
				modelMap.addAttribute("msgOne", "Learning area registered successfully !");
				return "enrollLearningAreaForm";
			}

			else {
				modelMap.addAttribute("msg", "learning Area already exists!");
				return "enrollLearningAreaForm";
			}

		}

		modelMap.addAttribute("msg", "something is not right please try again!");
		return "enrollLearningAreaForm";

	}

	// send subject teacher login form to browser
	@RequestMapping("/subjectTeacherLogin")
	public String subjectTeacherLogin() {
		return "subjectTeacherLoginForm";
	}

	// verify subject teacher
	@RequestMapping("/verifySubjectTeacher")
	public String verifySubjectTeacher(@RequestParam("phone_number") String phone_number,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {

		if (solidarityService.findSubjectTeacherUsingPhoneNumber(phone_number) == null) {
			modelMap.addAttribute("msg", "Subject teacher not Enrolled please enroll first!");

			return "subjectTeacherLoginForm";
		}

		else if (solidarityService.findSubjectTeacherUsingPhoneNumber(phone_number).getPassword().equals(password)) {

			SubjectTeacher subjectTeacher = solidarityService.findSubjectTeacherUsingPhoneNumber(phone_number);

			String subjectTeacherId = String.valueOf(subjectTeacher.getSubjectTeacherId());

			Cookie cookie = new Cookie("subjectTeacherId", subjectTeacherId);

			response.addCookie(cookie);
			List<Grade> gradesTaught = subjectTeacher.getGrades();
			List<LearningArea> learningAreasTaught = subjectTeacher.getLearning_areas();

			modelMap.addAttribute("grades", gradesTaught);
			modelMap.addAttribute("learningAreas", learningAreasTaught);
			modelMap.addAttribute("subjectTeacher", subjectTeacher);
			modelMap.addAttribute("gradeNumber", gradesTaught.size());
			modelMap.addAttribute("learningAreaNumber", learningAreasTaught.size());

			return "subjectTeacherFirstPage";
		}

		else {
			modelMap.addAttribute("msg", "Invalid  Phone number or password please Try again!");

		}

		return "subjectTeacherLoginForm";

	}

	// verify subject teacher duplicate
	@RequestMapping("/verifySubjectTeacherDuplicate")
	public String verifySubjectTeacherDuplicate(
			@CookieValue(value = "subjectTeacherId", defaultValue = "000") int subjectTeacherId, ModelMap modelMap,
			HttpServletResponse response) {

		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);

		String subjectTeacherIds = String.valueOf(subjectTeacher.getSubjectTeacherId());

		Cookie cookie = new Cookie("subjectTeacherId", subjectTeacherIds);

		response.addCookie(cookie);
		List<Grade> gradesTaught = subjectTeacher.getGrades();
		List<LearningArea> learningAreasTaught = subjectTeacher.getLearning_areas();

		modelMap.addAttribute("grades", gradesTaught);
		modelMap.addAttribute("learningAreas", learningAreasTaught);
		modelMap.addAttribute("subjectTeacher", subjectTeacher);
		modelMap.addAttribute("gradeNumber", gradesTaught.size());
		modelMap.addAttribute("learningAreaNumber", learningAreasTaught.size());

		return "subjectTeacherFirstPage";
	}

	// show grades in a class for subject teacher
	@RequestMapping("/showGrades")
	public String showGrades(@CookieValue(value = "subjectTeacherId", defaultValue = "000") int subjectTeacherId,
			ModelMap modelMap, @RequestParam(value="confirm",defaultValue="false") boolean confirm) {
		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);

		int schoolId = subjectTeacher.getSchool().getSchoolId();

		School school = solidarityService.findSchoolUsingSchoolId(schoolId);

		List<Grade> grades = school.getGrade();
		modelMap.addAttribute("grades", grades);

		if (confirm == true) {
			modelMap.addAttribute("msgOne", "Grade(s) is selected successfully!");
		}

		return "showGradesPage";
	}

	// saving the grade selected
	@RequestMapping("/saveGrades")
	public String SaveGrade(@RequestParam(value = "gradeIds", defaultValue = "") List<Integer> gradeIds,
			@CookieValue(value = "subjectTeacherId", defaultValue = "000") int subjectTeacherId, ModelMap modelMap) {

		if (gradeIds.isEmpty() == true || gradeIds == null) {
			modelMap.addAttribute("msg", "No grade selected please try again!");

			return "gradeSelectErrorPage";
		}

		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);

		List<Grade> grades = subjectTeacher.getGrades();

		List<Grade> gradesIn = new ArrayList<Grade>();

		for (int i = 0; i < gradeIds.size(); i++) {

			int gradeId = gradeIds.get(i);

			Grade gradeIn = solidarityService.findGradeUsingGradeId(gradeId);

			gradesIn.add(gradeIn);
		}

		if (grades.equals(gradesIn)) {
			modelMap.addAttribute("msg", "You already selected this/these grade(s)");

			return "gradeSelectErrorPage";
		}

		else if (grades.isEmpty() == true) {
			for (int i = 0; i < gradesIn.size(); i++) {

				Grade gradeIn = gradesIn.get(i);

				subjectTeacher.getGrades().add(gradeIn);

				gradeIn.getSubject_teachers().add(subjectTeacher);

				solidarityService.saveSubjectTeacher(subjectTeacher);
				return "redirect:/showGrades?confirm=true";

			}
		}

		else if (grades != gradesIn) {
			List<Grade> matchedGrades = new ArrayList<Grade>();
			List<Grade> uniqueGrades = new ArrayList<Grade>();

			for (int j = 0; j < gradesIn.size(); j++) {
				Grade gradeIn = gradesIn.get(j);

				for (int k = 0; k < grades.size(); k++) {
					Grade grade = grades.get(k);
					if (grade == gradeIn) {
						matchedGrades.add(grade);
					} else if (grade != gradeIn) {
						uniqueGrades.add(gradeIn);
					}
				}
			}

			if (matchedGrades.isEmpty() == true) {
				for (int m = 0; m < uniqueGrades.size(); m++) {
					Grade grade = uniqueGrades.get(m);

					subjectTeacher.getGrades().add(grade);

					grade.getSubject_teachers().add(subjectTeacher);

					solidarityService.saveSubjectTeacher(subjectTeacher);

				}
				return "redirect:/showGrades?confirm=true";
			}

			else if (matchedGrades.isEmpty() == false) {
				modelMap.addAttribute("msg", "You already selected this/these grade(s)");

				return "gradeSelectErrorPage";
			}

		}

		return "";

	}

	// send Headteacher login page to the browser
	@RequestMapping("/headTeacherLogin")
	public String headTeacherLogin(HttpServletRequest request) {

		HttpSession session = request.getSession();

		session.setAttribute("uname", "my name is head");

		return "headTeacherLoginForm";
	}

	// verifying the headTeacher
	@RequestMapping("/verifyHeadTeacher")
	public String verifyHeadTeacher(@RequestParam("phone_number") String phoneNumber,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response,
			HttpServletRequest request) {

	
		
	

		if (solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber) == null) {
			modelMap.addAttribute("msg", "HeadTeacher not enrolled please enroll first!");
			return "headTeacherLoginForm";
		}

		else if (solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber).getPassword().equals(password)) {

			HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);

			String headTeacherPhoneNumber = headTeacher.getPhoneNumber();

			Cookie cookie = new Cookie("headTeacherPhoneNumber", headTeacherPhoneNumber);

			response.addCookie(cookie);

			School school = headTeacher.getSchool();

			List<Learner> learnersInSchool = school.getLearners();
			List<SubjectTeacher> subjectTeachersInSchool = school.getSubject_teacher();
			List<Grade> gradesInSchool = school.getGrade();
			List<Grade> filteredGrade = new ArrayList<Grade>();
			
			
			List<String> learnersNames = new ArrayList<String>();
			
			
			for(int i=0;i<learnersInSchool.size();i++)
			{
				Learner learner = learnersInSchool.get(i);
				
				learnersNames.add(learner.getFirstName());
				
			}
			
			HashSet<String> actualLearnersNames = new HashSet<String>();
			actualLearnersNames.addAll(learnersNames);
			
			for (int i = 0; i < gradesInSchool.size(); i++) {
				Grade gradeInSchool = gradesInSchool.get(i);

				if (gradeInSchool.isBeenTermThree() == false) {
					filteredGrade.add(gradeInSchool);
				}

			}

			int learnersNumber = actualLearnersNames.size();
			int numberOfTeachers = subjectTeachersInSchool.size();
			int numberOfGrade = gradesInSchool.size();
			int accountNumber = headTeacher.getHead_teacher_id();

			modelMap.addAttribute("grades", filteredGrade);
			modelMap.addAttribute("numberOfTeachers", numberOfTeachers);
			modelMap.addAttribute("accountNumber", accountNumber);
			modelMap.addAttribute("learnersNumber", learnersNumber);
			modelMap.addAttribute("teachers", subjectTeachersInSchool);
			modelMap.addAttribute("numberOfGrade", numberOfGrade);
			modelMap.addAttribute("headTeacher", headTeacher);

			return "headTeacherFirstPage";
		}

		else {
			modelMap.addAttribute("msg", "Invalid phone number or password please try again !");
		}

		return "headTeacherLoginForm";
	}

	// back link for the head teacher
	@RequestMapping("/verifyHeadTeacherDuplicate")
	public String verifyHeadTeacherDuplicate(
			@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "00") String phoneNumber, ModelMap modelMap,
			HttpServletResponse response, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String msg = (String) session.getAttribute("uname");
		System.out.println(msg);

		HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);

		String headTeacherPhoneNumber = headTeacher.getPhoneNumber();

		Cookie cookie = new Cookie("headTeacherPhoneNumber", headTeacherPhoneNumber);

		response.addCookie(cookie);

		School school = headTeacher.getSchool();

		List<Learner> learnersInSchool = school.getLearners();
		List<SubjectTeacher> subjectTeachersInSchool = school.getSubject_teacher();
		List<Grade> gradesInSchool = school.getGrade();
		List<Grade> filteredGrade = new ArrayList<Grade>();

		for (int i = 0; i < gradesInSchool.size(); i++) {
			Grade gradeInSchool = gradesInSchool.get(i);

			if (gradeInSchool.isBeenTermThree() == false) {
				filteredGrade.add(gradeInSchool);
			}

		}
		
		
List<String> learnersNames = new ArrayList<String>();
		
		for(int i=0;i<learnersInSchool.size();i++)
		{
			Learner learner = learnersInSchool.get(i);
			learnersNames.add(learner.getFirstName());
			
		}
		
		
		HashSet<String> actualLearnersNames = new HashSet<String>();
		actualLearnersNames.addAll(learnersNames);
		

		int learnersNumber =actualLearnersNames.size();
		int numberOfTeachers = subjectTeachersInSchool.size();
		int numberOfGrade = gradesInSchool.size();
		int accountNumber = headTeacher.getHead_teacher_id();

		modelMap.addAttribute("grades", filteredGrade);
		modelMap.addAttribute("numberOfTeachers", numberOfTeachers);
		modelMap.addAttribute("accountNumber", accountNumber);
		modelMap.addAttribute("learnersNumber", learnersNumber);
		modelMap.addAttribute("teachers", subjectTeachersInSchool);
		modelMap.addAttribute("numberOfGrade", numberOfGrade);
		modelMap.addAttribute("headTeacher", headTeacher);

		return "headTeacherFirstPage";
	}

	// send select learning area form to the subject teacher
	@RequestMapping("/selectLearningAreaBySubjectTeacher")
	public String selectLearningArea(ModelMap modelMap) {
		return "selectLearningAreaForm";
	}

	// saving the learning Area from the subject Teacher
	@RequestMapping("/saveLearningAreaFromSubjectTeacher")
	public String saveLearningAreaFromSubjectTeacher(
			@CookieValue(value = "subjectTeacherId", defaultValue = "0000") int subjectTeacherId, ModelMap modelMap,
			@RequestParam("gradeNumber") int gradeNumber, @RequestParam("gradeStream") String gradeStream,
			@RequestParam("learningAreaName") String learningAreaName, @RequestParam("year") int year) {

		
		
		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);
		List<Grade> gradesTaughtBySybjectTeacher = subjectTeacher.getGrades();
		List<SubjectTeacher> subjectTeachers = new ArrayList<SubjectTeacher>();
		subjectTeachers.add(subjectTeacher);

		List<LearningArea> learningAreasTaught = solidarityService
				.findlearningAreaUsingSubjectTeachers(subjectTeachers);

		School school = subjectTeacher.getSchool();

		Grade grade = solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumber, gradeStream, school, year);
		if (grade == null) {
			modelMap.addAttribute("msg", "Grade not enrolled yet please contact the headteacher!");

			return "selectLearningAreaForm";
		}

		else {

			List<Grade> filteredGrades = new ArrayList<Grade>();

			for (int i = 0; i < gradesTaughtBySybjectTeacher.size(); i++) {
				Grade gradeTaughtBySubjectTeacher = gradesTaughtBySybjectTeacher.get(i);

				if (gradeTaughtBySubjectTeacher != grade) {

					filteredGrades.add(gradeTaughtBySubjectTeacher);
				}

			}

			if (filteredGrades.equals(gradesTaughtBySybjectTeacher) == true) {

				modelMap.addAttribute("msg", "Grade not previously selected Please Select the grade and Try again!");

				return "selectLearningAreaForm";

			}

			else {
				List<LearningArea> learningAreasInTheGrade = solidarityService.findLearningAreaUsingGrade(grade);

				if (learningAreasTaught.isEmpty() == true) {

					LearningArea learningArea = solidarityService.findLearningAreaUsingGradeAndLearningAreaName(grade,
							learningAreaName.toUpperCase());
					
					System.out.println("The learning are name :" + learningAreaName);
					
					if(learningArea == null)
					{
						modelMap.addAttribute("msg", "Learning Area not enrolled contact class teacher!");
					}
					else
					{
					learningArea.getSubjectTeachers().add(subjectTeacher);
					subjectTeacher.getLearning_areas().add(learningArea);

					solidarityService.saveSubjectTeacher(subjectTeacher);

					modelMap.addAttribute("msg", "Learning Area added successfully!");
					}

					return "selectLearningAreaForm";

				}

				List<LearningArea> filteredLearningAreas = new ArrayList<LearningArea>();

				for (int i = 0; i < learningAreasTaught.size(); i++) {
					LearningArea learningAreaTaught = learningAreasTaught.get(i);

					if (learningAreaTaught.getLearningAreaName().equals(learningAreaName.toUpperCase()) == false) {

						filteredLearningAreas.add(learningAreaTaught);
					}

				}

				if (learningAreasTaught.equals(filteredLearningAreas) == true) {
					List<LearningArea> filteredLearningAreasTwo = new ArrayList<LearningArea>();
					for (int i = 0; i < learningAreasInTheGrade.size(); i++) {
						LearningArea learningAreaInTheGrade = learningAreasInTheGrade.get(i);

						if (learningAreaInTheGrade.getLearningAreaName()
								.equals(learningAreaName.toUpperCase()) == false) {
							filteredLearningAreasTwo.add(learningAreaInTheGrade);
						}
					}

					if (filteredLearningAreasTwo.equals(learningAreasInTheGrade) == true) {
						System.out.println("Learning area not registered in grade please inform the class teacher");

						modelMap.addAttribute("msg",
								"Learning area not registered in grade please inform the class teacher!");
						return "selectLearningAreaForm";
					} else if (filteredLearningAreasTwo.equals(learningAreasInTheGrade) == false) {
						LearningArea learningArea = solidarityService
								.findLearningAreaUsingGradeAndLearningAreaName(grade, learningAreaName.toUpperCase());
						learningArea.getSubjectTeachers().add(subjectTeacher);
						subjectTeacher.getLearning_areas().add(learningArea);

						solidarityService.saveSubjectTeacher(subjectTeacher);

						modelMap.addAttribute("msg", "Learning Area added successfully!");

						return "selectLearningAreaForm";
					}

				}

				else if (learningAreasTaught.equals(filteredLearningAreas) == false) {
					modelMap.addAttribute("msg", "opps learning area already selected!");

					return "selectLearningAreaForm";
				}

			}

		}

		return "selectLearningAreaForm";
	}

	// send the assessLearnerFirstForm to the browser
	@RequestMapping("/showAssessLearnerFirstForm")
	public String showAssessLearnerFirstForm() {

		return "assessLearnerFirstForm";
	}

	// verify and send the general assesment sheet to the subject Teacher
	@RequestMapping("/verifyAssessLearner")
	public String verifyAssessLearnerRequest(
			@CookieValue(value = "subjectTeacherId", defaultValue = "0000") int subjectTeacherId,
			@RequestParam("gradeStream") String gradeStream, @RequestParam("gradeNumber") int gradeNumber,
			@RequestParam(value = "strandName") String strandName, @RequestParam(value = "year") int year,
			@RequestParam(value = "subStrandName") String subStrandName,
			@RequestParam("learningAreaName") String learningAreaName, ModelMap modelMap, HttpServletResponse response,
			HttpServletRequest request, Model model) {

		System.out.println("The grade number: " + gradeNumber);
		System.out.println("The grade stream: " + gradeStream);
		System.out.println("The substrand is " + subStrandName);

		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);

		School school = subjectTeacher.getSchool();

		Grade gradeFromInput = solidarityService.findGradeUsingNumberStreamSchoolYear(gradeNumber,
				gradeStream.toUpperCase(), school, year);

		if (gradeFromInput == null) {
			modelMap.addAttribute("msg",
					"You have not selected this grade or it has not been enrolled please contact the Headteacher !");
			return "assessLearnerFirstForm";
		}

		List<Grade> gradesTaught = subjectTeacher.getGrades();
		List<Grade> filteredGrades = new ArrayList<Grade>();

		for (int i = 0; i < gradesTaught.size(); i++) {
			Grade gradeTaught = gradesTaught.get(i);

			if (gradeTaught.getGradeStream().equals(gradeFromInput.getGradeStream()) == false
					&& gradeTaught.getGradeNumber() != gradeFromInput.getGradeNumber()) {
				filteredGrades.add(gradeTaught);
			}

		}

		if (filteredGrades.equals(gradesTaught) == true) {
			modelMap.addAttribute("msg",
					"You have not selected this grade or it has not been enrolled please contact the Headteacher !");
			return "assessLearnerFirstForm";
		}

		else {
			List<LearningArea> learningAreasTaught = subjectTeacher.getLearning_areas();
			List<LearningArea> filteredLearningAreas = new ArrayList<LearningArea>();
			System.out.println("here too");
			LearningArea learningAreaFromInput = solidarityService
					.findLearningAreaUsingGradeAndLearningAreaName(gradeFromInput, learningAreaName.toUpperCase());
			if (learningAreaFromInput == null) {

				modelMap.addAttribute("msg",
						"Learning Area has not been registered  please inform the class teacher !");
				return "assessLearnerFirstForm";

			}

			for (int i = 0; i < learningAreasTaught.size(); i++) {
				LearningArea learningAreaTaught = learningAreasTaught.get(i);

				if (learningAreaTaught.equals(learningAreaFromInput) == false) {
					filteredLearningAreas.add(learningAreaTaught);
				}

			}

			if (learningAreasTaught.equals(filteredLearningAreas) == true) {
				modelMap.addAttribute("msg",
						"You have not selected this learning Area in the select learning Area option!");
				return "assessLearnerFirstForm";
			} else {

				if (strandName == "") {
					modelMap.addAttribute("msg", "Strand name is not valid!");
					return "assessLearnerFirstForm";
				}

				else {

					if (subStrandName == "") {
						modelMap.addAttribute("msg", "Sub Strand is empty do you want to continue ?");

						Cookie cookie = new Cookie("gradeId", String.valueOf(gradeFromInput.getGradeId()));
						response.addCookie(cookie);

						Cookie cookieTwo = new Cookie("learningAreaId",
								String.valueOf(learningAreaFromInput.getLearningAreaId()));
						response.addCookie(cookieTwo);

						HttpSession session = request.getSession();
						session.setAttribute("strandName", strandName);

						return "confirmationPage";
					} else {

						Cookie cookie = new Cookie("gradeId", String.valueOf(gradeFromInput.getGradeId()));
						response.addCookie(cookie);

						Strand strand = solidarityService
								.findStrandUsingLearningAreaIdAndStrandName(learningAreaFromInput, strandName);

						if (strand == null) {

							Strand strandFromInput = new Strand();

							strandFromInput.setStrandName(strandName);
							strandFromInput.setLearningArea(learningAreaFromInput);

							solidarityService.saveStrand(strandFromInput);

							SubStrand subStrand = new SubStrand();

							subStrand.setSubStrandName(subStrandName);

							subStrand.setStrand(strandFromInput);

							solidarityService.saveSubStrand(subStrand);

							List<Learner> learners = solidarityService.findLearnerUsingGradeId(gradeFromInput);

							for (int i = 0; i < learners.size(); i++) {
								Learner learner = learners.get(i);

								learner.setIndicateEvaluation(0);

								solidarityService.saveLearner(learner);

							}

							modelMap.addAttribute("learners", learners);
							modelMap.addAttribute("grade", gradeFromInput);
							modelMap.addAttribute("learningArea", learningAreaFromInput);
							modelMap.addAttribute("strand", strandFromInput);
							modelMap.addAttribute("subStrand", subStrand);

							return "generalAssessmentSheet";
						}

						else {

							SubStrand subStrand = new SubStrand();

							subStrand.setSubStrandName(subStrandName);

							subStrand.setStrand(strand);

							solidarityService.saveSubStrand(subStrand);

							List<Learner> learners = solidarityService.findLearnerUsingGradeId(gradeFromInput);

							for (int i = 0; i < learners.size(); i++) {
								Learner learner = learners.get(i);

								learner.setIndicateEvaluation(0);

								solidarityService.saveLearner(learner);

							}

							modelMap.addAttribute("learners", learners);
							modelMap.addAttribute("grade", gradeFromInput);
							modelMap.addAttribute("learningArea", learningAreaFromInput);
							modelMap.addAttribute("strand", strand);
							modelMap.addAttribute("subStrand", subStrand);

							return "generalAssessmentSheet";
						}

					}

				}

			}

		}
	}

	// saving the default substrand in the event that subject teacher accepts not to
	// use substrandName

	// part 2
	@RequestMapping("/confirmationResponse")
	public String savingSubStrandTwo(ModelMap modelMap, @RequestParam("subStrandName") String subStrandName,
			HttpServletRequest request,
			@CookieValue(value = "learningAreaId", defaultValue = "0000") int learningAreaId,
			@CookieValue(value = "gradeId", defaultValue = "0000") int gradeId, Model model) {

		System.out.println("The substrand name is " + subStrandName);

		HttpSession session = request.getSession(false);
		String strandName = (String) session.getAttribute("strandName");

		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

		Strand strand = solidarityService.findStrandUsingLearningAreaIdAndStrandName(learningArea, strandName);

		if (strand == null) {

			Strand strandFromInput = new Strand();

			strandFromInput.setStrandName(strandName);
			strandFromInput.setLearningArea(learningArea);

			solidarityService.saveStrand(strandFromInput);

			SubStrand subStrand = new SubStrand();

			subStrand.setSubStrandName(subStrandName);

			subStrand.setStrand(strandFromInput);

			solidarityService.saveSubStrand(subStrand);

			Grade gradeFromInput = solidarityService.findGradeUsingGradeId(gradeId);
			List<Learner> learners = solidarityService.findLearnerUsingGradeId(gradeFromInput);

			for (int i = 0; i < learners.size(); i++) {
				Learner learner = learners.get(i);

				learner.setIndicateEvaluation(0);

				solidarityService.saveLearner(learner);

			}

			modelMap.addAttribute("learners", learners);
			modelMap.addAttribute("grade", gradeFromInput);
			modelMap.addAttribute("learningArea", learningArea);
			modelMap.addAttribute("strand", strandFromInput);
			modelMap.addAttribute("subStrand", subStrand);

			return "generalAssessmentSheet";
		}

		else {

			SubStrand subStrand = new SubStrand();

			subStrand.setSubStrandName(subStrandName);

			subStrand.setStrand(strand);

			solidarityService.saveSubStrand(subStrand);

			Grade gradeFromInput = solidarityService.findGradeUsingGradeId(gradeId);

			List<Learner> learners = solidarityService.findLearnerUsingGradeId(gradeFromInput);

			for (int i = 0; i < learners.size(); i++) {
				Learner learner = learners.get(i);

				learner.setIndicateEvaluation(0);

				solidarityService.saveLearner(learner);

			}

			modelMap.addAttribute("learners", learners);
			modelMap.addAttribute("grade", gradeFromInput);
			modelMap.addAttribute("learningArea", learningArea);
			modelMap.addAttribute("strand", strand);
			modelMap.addAttribute("subStrand", subStrand);

			return "generalAssessmentSheet";
		}

	}

	// saving the scores of the learner
	@RequestMapping("/saveScores")
	public String saveScores(@RequestParam("learningAreaId") String learningAreaId,
			@RequestParam("strandId") String strandId, @RequestParam("subStrandId") String subStrandId,
			@RequestParam("score") String score, @RequestParam("learnerId") String learnerId, ModelMap modelMap,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setAttribute("subStrandId", subStrandId);
		session.setAttribute("strandId", strandId);
		session.setAttribute("learningAreaId", learningAreaId);

		Learner learner = solidarityService.findLearnerUsingLearnerId(Integer.parseInt(learnerId));

		session.setAttribute("learnerId", learner.getLearnerId());

		LearningArea learningArea = solidarityService.findLearningAreaUsingId(Integer.parseInt(learningAreaId));

		Score scoreIn = new Score();
		scoreIn.setScore_value(Integer.parseInt(score));

		SubStrand subStrand = solidarityService.findSubStrandUsingSubStrandId(Integer.parseInt(subStrandId));

		Score scoreDb = solidarityService.findScoreUsingLearnerIdAndSubStrand(learner, subStrand);

		if (scoreDb == null) {

			learner.setIndicateEvaluation(Integer.parseInt(score));
			solidarityService.saveLearner(learner);
			scoreIn.setSubStrand(subStrand);
			scoreIn.setLearner(learner);
			solidarityService.saveScore(scoreIn);
			solidarityService.saveLearner(learner);
			subStrand.getScores().add(scoreIn);
			solidarityService.saveSubStrand(subStrand);
			return "redirect:/displayStatus";
		}

		else {
			System.out.println("Score db value" + scoreDb.getScore_value() + learner.getFirstName());
			return "redirect:/displayStatus";
		}

	}

	// displaying the status of the general assessment sheet()
	@RequestMapping("/displayStatus")
	public String displayStatus(HttpServletRequest request,
			@CookieValue(value = "gradeId", defaultValue = "000") int gradeId, ModelMap modelMap) {

		System.out.println("This is the grade id" + gradeId);

		HttpSession session = request.getSession(false);

		String learningAreaId = (String) session.getAttribute("learningAreaId");
		int learningAreaIdInt = Integer.parseInt(learningAreaId);

		String subStrandId = (String) session.getAttribute("subStrandId");
		int subStrandIdInt = Integer.parseInt(subStrandId);

		String strandId = (String) session.getAttribute("strandId");
		int strandIdInt = Integer.parseInt(strandId);

		int learnerId = (int) session.getAttribute("learnerId");

		SubStrand subStrand = solidarityService.findSubStrandUsingSubStrandId(subStrandIdInt);
		Strand strand = solidarityService.findStrandUsingStrandId(strandIdInt);
		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaIdInt);
		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);
		List<Learner> learners = solidarityService.findLearnerUsingGradeId(grade);

		System.out.println("The list is empty" + learners.isEmpty());

		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);
		modelMap.addAttribute("learningArea", learningArea);
		modelMap.addAttribute("strand", strand);
		modelMap.addAttribute("subStrand", subStrand);

		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		Score score = solidarityService.findScoreUsingLearnerIdAndSubStrand(learner, subStrand);

		modelMap.addAttribute("score", score);

		return "generalAssessmentSheet";
	}

	@RequestMapping("/viewLearnerProfile")
	public String viewLearnerAcademics(@RequestParam("learnerId") int learnerId, ModelMap modelMap,
			HttpServletResponse response) {

		System.out.println("This is the learner id" + learnerId);
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));
		response.addCookie(cookie);

		List<LearningArea> learningAreas = learner.getLearning_areas();
		List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

		for (int i = 0; i < learningAreas.size(); i++) {

			LearningArea learningArea = learningAreas.get(i);
			System.out.println("The learning area name is " + learningArea.getLearningAreaName());
			int overalScorePerLearningArea;
			List<Strand> strands = learningArea.getStrands();

			if (strands.isEmpty() == true) {
				learningArea.setAverageScore(0);
				evaluatedLearningAreas.add(learningArea);
				System.out.println("Base 1");
				System.out.println("The overal score in " + learningArea.getLearningAreaName() + " is "
						+ learningArea.getAverageScore());
			}

			else if (strands.isEmpty() == false) {
				List<Integer> strandValues = new ArrayList<Integer>();

				int totalStrandMarks = 0;

				for (int j = 0; j < strands.size(); j++) {
					Strand strand = strands.get(j);

					System.out.println("The strand Name is" + strand.getStrandName());

					List<SubStrand> subStrands = strand.getSub_strands();

					if (subStrands.isEmpty() == true) {
						int totalSubStrandMarks = 0;

						int overalScorePerStrand = Math.round(totalSubStrandMarks + 0);

						strandValues.add(overalScorePerStrand);
					}

					else if (subStrands.isEmpty() == false) {
						int totalSubStrandMarks = 0;
						List<Integer> scoreValues = new ArrayList<Integer>();

						for (int k = 0; k < subStrands.size(); k++) {
							SubStrand subStrand = subStrands.get(k);

							List<Score> scores = subStrand.getScores();

							System.out.println("The substrand size is" + subStrands.size());
							System.out.println(
									subStrand.getSubStrandName() + " The scores is empty ?" + scores.isEmpty());

							if (scores.isEmpty() == true) {
								scoreValues.add(0);
								System.out.println("its all me");

							}

							else if (scores.isEmpty() == false) {
								for (int x = 0; x < scores.size(); x++) {
									Score score = scores.get(x);

									if (score == null) {
										Score scoreOne = new Score();
										scoreOne.setScore_value(0);

										scoreValues.add(scoreOne.getScore_value());
										System.out.println("This is it  not not now");
									}

									else if (score != null) {
										if (score.getLearner().getLearnerId() == learner.getLearnerId()) {
											scoreValues.add(score.getScore_value());
											System.out.println("This is it now" + score.getScore_value());
										}
									}

								}
							}
						}

						for (int l = 0; l < scoreValues.size(); l++) {
							totalSubStrandMarks = totalSubStrandMarks + scoreValues.get(l);
						}

						System.out.println("The total substrandMarks is " + totalSubStrandMarks);
						System.out.println("The scoreValue size is " + scoreValues.size());

						int overalScorePerStrand;
						if (scoreValues.size() != 0) {

							overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

							strandValues.add(overalScorePerStrand);
						}

						else {
							overalScorePerStrand = 0;
						}

					}

				}

				for (int k = 0; k < strandValues.size(); k++) {
					totalStrandMarks += strandValues.get(k);

				}

				System.out.println("The total marks is " + totalStrandMarks);

				System.out.println("The strandValue size is" + strandValues.size());

				overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

				learningArea.setAverageScore(overalScorePerLearningArea);

				switch (overalScorePerLearningArea) {
				case 1:
					learningArea.setRubric("B.E");
					System.out.println("Below expectations");
					break;
				case 2:
					learningArea.setRubric("A.E");
					System.out.println("Approaches expectations");
					break;
				case 3:
					learningArea.setRubric("M.E");
					System.out.println("Meets expectations");
					break;
				case 4:
					learningArea.setRubric("E.E");
					System.out.println("Exceeds expectations");
					break;

				}

				solidarityService.saveLearningArea(learningArea);

				evaluatedLearningAreas.add(learningArea);

			}
		}

		List<Integer> learningAreaScores = new ArrayList<Integer>();

		for (int i = 0; i < evaluatedLearningAreas.size(); i++) {
			LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(i);

			learningAreaScores.add(evalueatedLearningArea.getAverageScore());

			System.out.println(evalueatedLearningArea.getLearningAreaName() + evalueatedLearningArea.getAverageScore());
		}

		int totalPoint = 0;

		for (int i = 0; i < learningAreaScores.size(); i++) {
			totalPoint += learningAreaScores.get(i);
		}

		if (learningAreaScores.size() == 0) {
			int overalPoint = 0;
			learner.setOveralPoint(overalPoint);

		} else {
			int overalPoint = Math.round(totalPoint / learningAreaScores.size());

			learner.setOveralPoint(overalPoint);
		}

		solidarityService.saveLearner(learner);

		System.out.println("The learner is a good student he/she scored " + learner.getOveralPoint());
		modelMap.addAttribute("learningAreas", evaluatedLearningAreas);
		modelMap.addAttribute("learner", learner);
		modelMap.addAttribute("classTeacher", learner.getGrade().getClass_teacher());

		String path = servletContext.getRealPath("/");
		List<Data> data = new ArrayList<Data>();
		int term = learner.getGrade().getTerm();

		switch (term) {
		case 1:
			int termOneScore = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataOne = new Data();
			dataOne.setScore(termOneScore);
			dataOne.setTerm("One");
			data.add(dataOne);
			generateBarGraph.drawBarGraph(path, data);

			break;
		case 2:
			int scoreOne = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataTwo = new Data();
			dataTwo.setScore(scoreOne);
			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(calculateResults.generateLearnerOveralScore(learner, 2));
			dataThree.setTerm("Two");
			data.add(dataThree);

			generateBarGraph.drawBarGraph(path, data);

			break;
		case 3:
			int scoreTwo = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataFour = new Data();
			dataFour.setScore(scoreTwo);
			dataFour.setTerm("One");
			data.add(dataFour);

			Data dataFive = new Data();
			dataFive.setScore(calculateResults.generateLearnerOveralScore(learner, 2));
			dataFive.setTerm("Two");
			data.add(dataFive);

			Data dataSix = new Data();
			dataSix.setScore(calculateResults.generateLearnerOveralScore(learner, 3));
			dataSix.setTerm("Three");
			data.add(dataSix);
			generateBarGraph.drawBarGraph(path, data);

			break;
		}

		return "learnerMarksPage";
	}

	@RequestMapping("/viewLearnerProfileDuplicate")
	public String viewLearnerAcademicsDuplicate(@CookieValue(value = "learnerId", defaultValue = "000") int learnerId,
			ModelMap modelMap, HttpServletResponse response, @RequestParam("confirm") Boolean confirm) {

		System.out.println("This is the learner id" + learnerId);
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));
		response.addCookie(cookie);

		List<LearningArea> learningAreas = learner.getLearning_areas();
		List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

		for (int i = 0; i < learningAreas.size(); i++) {

			LearningArea learningArea = learningAreas.get(i);
			System.out.println("The learning area name is " + learningArea.getLearningAreaName());
			int overalScorePerLearningArea;
			List<Strand> strands = learningArea.getStrands();

			if (strands.isEmpty() == true) {
				learningArea.setAverageScore(0);
				evaluatedLearningAreas.add(learningArea);
				System.out.println("Base 1");
				System.out.println("The overal score in " + learningArea.getLearningAreaName() + " is "
						+ learningArea.getAverageScore());
			}

			else if (strands.isEmpty() == false) {
				List<Integer> strandValues = new ArrayList<Integer>();

				int totalStrandMarks = 0;

				for (int j = 0; j < strands.size(); j++) {
					Strand strand = strands.get(j);

					System.out.println("The strand Name is" + strand.getStrandName());

					List<SubStrand> subStrands = strand.getSub_strands();

					if (subStrands.isEmpty() == true) {
						int totalSubStrandMarks = 0;

						int overalScorePerStrand = Math.round(totalSubStrandMarks + 0);

						strandValues.add(overalScorePerStrand);
					}

					else if (subStrands.isEmpty() == false) {
						int totalSubStrandMarks = 0;
						List<Integer> scoreValues = new ArrayList<Integer>();

						for (int k = 0; k < subStrands.size(); k++) {
							SubStrand subStrand = subStrands.get(k);

							List<Score> scores = subStrand.getScores();

							System.out.println("The substrand size is" + subStrands.size());
							System.out.println(
									subStrand.getSubStrandName() + " The scores is empty ?" + scores.isEmpty());

							if (scores.isEmpty() == true) {
								scoreValues.add(0);
								System.out.println("its all me");

							}

							else if (scores.isEmpty() == false) {
								for (int x = 0; x < scores.size(); x++) {
									Score score = scores.get(x);

									if (score == null) {
										Score scoreOne = new Score();
										scoreOne.setScore_value(0);

										scoreValues.add(scoreOne.getScore_value());
										System.out.println("This is it  not not now");
									}

									else if (score != null) {
										if (score.getLearner().getLearnerId() == learner.getLearnerId()) {
											scoreValues.add(score.getScore_value());
											System.out.println("This is it now" + score.getScore_value());
										}
									}

								}
							}
						}

						for (int l = 0; l < scoreValues.size(); l++) {
							totalSubStrandMarks = totalSubStrandMarks + scoreValues.get(l);
						}

						System.out.println("The total substrandMarks is " + totalSubStrandMarks);
						System.out.println("The scoreValue size is " + scoreValues.size());

						int overalScorePerStrand;
						if (scoreValues.size() != 0) {

							overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

							strandValues.add(overalScorePerStrand);
						}

						else {
							overalScorePerStrand = 0;
						}
						strandValues.add(overalScorePerStrand);

					}

				}

				for (int k = 0; k < strandValues.size(); k++) {
					totalStrandMarks += strandValues.get(k);

				}

				System.out.println("The total marks is " + totalStrandMarks);

				System.out.println("The strandValue size is" + strandValues.size());

				overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

				learningArea.setAverageScore(overalScorePerLearningArea);

				switch (overalScorePerLearningArea) {
				case 1:
					learningArea.setRubric("B.E");
					System.out.println("Below expectations");
					break;
				case 2:
					learningArea.setRubric("A.E");
					System.out.println("Approaches expectations");
					break;
				case 3:
					learningArea.setRubric("M.E");
					System.out.println("Meets expectations");
					break;
				case 4:
					learningArea.setRubric("E.E");
					System.out.println("Exceeds expectations");
					break;

				}

				solidarityService.saveLearningArea(learningArea);

				evaluatedLearningAreas.add(learningArea);

			}
		}

		List<Integer> learningAreaScores = new ArrayList<Integer>();

		for (int i = 0; i < evaluatedLearningAreas.size(); i++) {
			LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(i);

			learningAreaScores.add(evalueatedLearningArea.getAverageScore());

			System.out.println(evalueatedLearningArea.getLearningAreaName() + evalueatedLearningArea.getAverageScore());
		}

		int totalPoint = 0;

		for (int i = 0; i < learningAreaScores.size(); i++) {
			totalPoint += learningAreaScores.get(i);
		}

		if (learningAreaScores.size() == 0) {
			int overalPoint = 0;
			learner.setOveralPoint(overalPoint);

		} else {
			int overalPoint = Math.round(totalPoint / learningAreaScores.size());

			learner.setOveralPoint(overalPoint);
		}

		solidarityService.saveLearner(learner);

		System.out.println("The learner is a good student he/she scored " + learner.getOveralPoint());
		modelMap.addAttribute("learningAreas", evaluatedLearningAreas);
		modelMap.addAttribute("learner", learner);
		modelMap.addAttribute("classTeacher", learner.getGrade().getClass_teacher());

		String path = servletContext.getRealPath("/");
		List<Data> data = new ArrayList<Data>();
		int term = learner.getGrade().getTerm();

		switch (term) {
		case 1:
			int termOneScore = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataOne = new Data();
			dataOne.setScore(termOneScore);
			dataOne.setTerm("One");
			data.add(dataOne);
			generateBarGraph.drawBarGraph(path, data);

			break;
		case 2:
			int scoreOne = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataTwo = new Data();
			dataTwo.setScore(scoreOne);
			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(calculateResults.generateLearnerOveralScore(learner, 2));
			dataThree.setTerm("Two");
			data.add(dataThree);

			generateBarGraph.drawBarGraph(path, data);

			break;
		case 3:
			int scoreTwo = calculateResults.generateLearnerOveralScore(learner, 1);
			Data dataFour = new Data();
			dataFour.setScore(scoreTwo);
			dataFour.setTerm("One");
			data.add(dataFour);

			Data dataFive = new Data();
			dataFive.setScore(calculateResults.generateLearnerOveralScore(learner, 2));
			dataFive.setTerm("Two");
			data.add(dataFive);

			Data dataSix = new Data();
			dataSix.setScore(calculateResults.generateLearnerOveralScore(learner, 3));
			dataSix.setTerm("Three");
			data.add(dataSix);
			generateBarGraph.drawBarGraph(path, data);

			break;
		}

		if (confirm == true) {
			modelMap.addAttribute("msgOne", "Strands not yet available!");
		}
		return "learnerMarksPage";
	}

	// ing the learner to the learning Areas
	// the view not user friendly
	@RequestMapping("/showLearningAreaListPage")
	public String showLearningAreaListPage(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,
			ModelMap modelMap,@RequestParam(value="confirm",defaultValue="false") Boolean confirm) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();

		List<LearningArea> learningAreas = grade.getLearning_areas();
		modelMap.addAttribute("learningAreas", learningAreas);
		
		
		if(confirm == true)
		{
			modelMap.addAttribute("msg", "All learners matched successfully!");
		}

		return "learningAreaListPage";
	}

	// matching the learners to the learning areas
	// the view has a problem
	@RequestMapping("/matchLearnersToLearningArea")
	public String matchLearnersToLearningAreA(@RequestParam("learningAreaId") int learningAreaId,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,ModelMap modelMap) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();
		System.out.println(grade.getGradeNumber() + grade.getGradeStream());

		List<Learner> learners = grade.getLearners();

		System.out.println(learners.isEmpty());

		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

		for (int i = 0; i < learners.size(); i++) {
			Learner learner = learners.get(i);
			
			 List<LearningArea> learnerLearningAreas = learner.getLearning_areas();
			 List<LearningArea> filteredLearningAreas = new ArrayList<LearningArea>();
			 
			 for(int x=0;x<learnerLearningAreas.size();x++)
			 {
				 LearningArea learnerLearningArea = learnerLearningAreas.get(x);
				 
				 if(learningArea.getLearningAreaId()==learnerLearningArea.getLearningAreaId())
				 {
					 filteredLearningAreas.add(learnerLearningArea);
				 }
				 
				 
			 }
			 
			 if(filteredLearningAreas.isEmpty()==true)
			 {
				 learningArea.getLearner().add(learner);
					learner.getLearning_areas().add(learningArea);

					solidarityService.saveLearner(learner);
			 }
			 
			 else
			 {
				 System.out.println(learningArea.getLearningAreaName() + " already matched");
			 }
			 

			
		}
		
		

		return "redirect:/showLearningAreaListPage?confirm=true";
	}

	// return start term page to the browser
	@RequestMapping("/startTerm")
	public String startTerm() {
		return "startTermPage";
	}

	// saving the start term request
	@RequestMapping("/initiateTerm")
	public String initiateTerm(ModelMap modelMap,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,
			@RequestParam("termNumber") int termNumber) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();

		System.out.println("The term " + grade.getTerm() + " had ended" + grade.getTermEnded());
		System.out.println("The term entered is " + termNumber);

		if (grade.getTermEnded() == null) {
			grade.setTermEnded(false);
		}

		if (grade.getTerm() == null) {
			if (termNumber == 1) {
				grade.setTerm(termNumber);
				grade.setBeenTermOne(true);
				grade.setTermEnded(false);
				solidarityService.saveGrade(grade);
				return "successPage";
			}

			else {
				modelMap.addAttribute("msg", "Please select term 1");

			}
		} else if (grade.getTerm() == 3 && grade.getTermEnded() == false) {
			modelMap.addAttribute("msg", "Term 3 has not yet ended please end term in the main tab menu");
		}

		else if (grade.getTerm() == 3 && grade.getTermEnded() == true) {

			modelMap.addAttribute("msg", "Term 3 has ended please select move to next grade in the main tab menu");

		} else if (grade.getTerm() == 2 && grade.getTermEnded() == false) {
			modelMap.addAttribute("msg", "Term 2 has not yet ended please end term in the main tab menu");
		}

		else if (grade.getTerm() == 2 && grade.getTermEnded() == true) {

			if (termNumber == 3) {
				grade.setTerm(termNumber);
				grade.setBeenTermThree(true);
				grade.setTermEnded(false);
				solidarityService.saveGrade(grade);
				return "successPage";
			} else {
				modelMap.addAttribute("msg", "Term 2 has not yet ended or you have picked to start the wrong term");
			}
		}

		else if (grade.getTerm() == 1 && grade.getTermEnded() == true) {
			if (grade.getTermEnded() == null) {

			}

			if (termNumber == 2) {
				grade.setTerm(termNumber);
				grade.setBeenTermTwo(true);
				grade.setTermEnded(false);
				solidarityService.saveGrade(grade);
				return "successPage";
			} else {
				modelMap.addAttribute("msg", "You cannot start another term apart from term 2 please try again!");
			}
		}

		else if (grade.getTerm() == 1 && grade.getTermEnded() == false) {

			modelMap.addAttribute("msg", "Term 1 has not yet ended");
		} else if (grade.getTerm() == 0) {
			if (termNumber == 1) {
				grade.setTerm(termNumber);
				grade.setBeenTermOne(true);
				grade.setTermEnded(false);
				solidarityService.saveGrade(grade);
				return "successPage";
			}

			else {
				modelMap.addAttribute("msg", "You cannot skip term 1");
			}
		}

		else {

			String msg = "Term " + grade.getTerm() + " has not yet ended please end term in the main tab menu ";

			modelMap.addAttribute("msg", msg);
		}

		return "startTermPage";
	}

	// sending end term page to the browser
	@RequestMapping("/endTermRequest")
	public String showEndTermPage(ModelMap modelMap,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();
		modelMap.addAttribute("term", grade.getTerm());
		return "endTermPage";
	}

	// Matching all the learners with learning areas
	@RequestMapping("/matchAll")
	public String matchAll(ModelMap modelMap,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();
		

		

		List<Learner> learnersInGrade = grade.getLearners();

		List<LearningArea> learningAreasInGrade = grade.getLearning_areas();
		
		
		for (int i = 0; i < learnersInGrade.size(); i++) {
			Learner learnerInGrade = learnersInGrade.get(i);
			
			List<LearningArea> learnerLearningAreas = learnerInGrade.getLearning_areas();
			 
			 for(int x=0;x<learnerLearningAreas.size();x++)
			 {
				 List<LearningArea> filteredLearningAreas = new ArrayList<>();
				 
				 LearningArea learnerLearningArea = learnerLearningAreas.get(x);
				 
				 for(int y = 0;y<learningAreasInGrade.size();y++)
				 {
					 LearningArea learningArea = learningAreasInGrade.get(y);
					 
					 if(learningArea.getLearningAreaId()==learnerLearningArea.getLearningAreaId())
					 {
						 filteredLearningAreas.add(learnerLearningArea);
					 }
				 }
				 
				 if(filteredLearningAreas.isEmpty()==true)
				 {
					 learnerLearningArea.getLearner().add(learnerInGrade);
						learnerInGrade.getLearning_areas().add(learnerLearningArea);

						solidarityService.saveLearner(learnerInGrade);
				 }
				 
				 else
				 {
					 System.out.println(learnerLearningArea.getLearningAreaName() + " already matched");
				 }
				 
				 
				 
			 }
			 
			
			}

		

		return "successPage";
	}

	// confirm end term request
	@RequestMapping("/confirmEndTerm")
	public String confirmEndTermRequest(ModelMap modelMap,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();
		grade.setTermEnded(true);

		solidarityService.saveGrade(grade);

		List<Learner> learnersInGrade = grade.getLearners();

		for (int i = 0; i < learnersInGrade.size(); i++) {
			Learner learnerInGrade = learnersInGrade.get(i);

			System.out.println(learnerInGrade.getFirstName());

			List<LearningArea> learningAreas = learnerInGrade.getLearning_areas();

			for (int y = 0; y < learningAreas.size(); y++) {
				LearningArea learningArea = learningAreas.get(y);
				System.out.println("The learning area name is " + learningArea.getLearningAreaName());
				int overalScorePerLearningArea;
				List<Strand> strands = learningArea.getStrands();
				if (strands.isEmpty() == true) {
					int term = grade.getTerm();

					switch (term) {
					case 1:

						System.out.println("Overal term 1 is 0");
						ResultRecord resultRecord = new ResultRecord();
						resultRecord.setLearner(learnerInGrade);
						resultRecord.setLearningAreaName(learningArea.getLearningAreaName());
						resultRecord.setOveralTermOneScore(0);
						solidarityService.saveResultRecord(resultRecord);

						break;
					case 2:
						System.out.println("Overal term 2 is 0");
						ResultRecord resultRecordTwo = solidarityService
								.findResultRecordUsingLearnerIdAndLearningAreaName(learnerInGrade,
										learningArea.getLearningAreaName());
						if (resultRecordTwo == null) {
							ResultRecord resultRecordNewTwo = new ResultRecord();
							resultRecordNewTwo.setLearner(learnerInGrade);
							resultRecordNewTwo.setLearningAreaName(learningArea.getLearningAreaName());
							resultRecordNewTwo.setOveralTermTwoScore(0);
							solidarityService.saveResultRecord(resultRecordNewTwo);

						} else {
							resultRecordTwo.setOveralTermTwoScore(0);
							solidarityService.saveResultRecord(resultRecordTwo);
						}

						break;
					case 3:

						System.out.println("Overal term 3 is 0");
						ResultRecord resultRecordThree = solidarityService
								.findResultRecordUsingLearnerIdAndLearningAreaName(learnerInGrade,
										learningArea.getLearningAreaName());
						if (resultRecordThree == null) {
							ResultRecord resultRecordNewThree = new ResultRecord();
							resultRecordNewThree.setLearner(learnerInGrade);
							resultRecordNewThree.setLearningAreaName(learningArea.getLearningAreaName());
							resultRecordNewThree.setOveralTermThreeScore(0);
							solidarityService.saveResultRecord(resultRecordNewThree);

						}

						else {
							resultRecordThree.setOveralTermThreeScore(0);
							solidarityService.saveResultRecord(resultRecordThree);
						}
						break;
					}

				}

				else if (strands.isEmpty() == false) {
					List<Integer> strandValues = new ArrayList<Integer>();
					int totalStrandMarks = 0;
					for (int j = 0; j < strands.size(); j++) {
						Strand strand = strands.get(j);
						System.out.println("The strand Name is" + strand.getStrandName());
						List<SubStrand> subStrands = strand.getSub_strands();
						if (subStrands.isEmpty() == true) {
							int totalSubStrandMarks = 0;

							int overalScorePerStrand = Math.round(totalSubStrandMarks + 0);

							strandValues.add(overalScorePerStrand);
						}

						else if (subStrands.isEmpty() == false) {
							int totalSubStrandMarks = 0;
							List<Integer> scoreValues = new ArrayList<Integer>();

							for (int k = 0; k < subStrands.size(); k++) {
								SubStrand subStrand = subStrands.get(k);

								List<Score> scores = subStrand.getScores();

								System.out.println("The substrand size is" + subStrands.size());
								System.out.println(
										subStrand.getSubStrandName() + " The scores is empty ?" + scores.isEmpty());

								if (scores.isEmpty() == true) {
									scoreValues.add(0);
									System.out.println("its all me");

								}

								else if (scores.isEmpty() == false) {
									for (int x = 0; x < scores.size(); x++) {
										Score score = scores.get(x);

										if (score == null) {
											Score scoreOne = new Score();
											scoreOne.setScore_value(0);

											scoreValues.add(scoreOne.getScore_value());
											System.out.println("This is it  not not now");
										}

										else if (score != null) {
											if (score.getLearner().getLearnerId() == learnerInGrade.getLearnerId()) {
												scoreValues.add(score.getScore_value());
												System.out.println("This is it now" + score.getScore_value());
											}
										}

									}
								}
							}

							for (int l = 0; l < scoreValues.size(); l++) {
								totalSubStrandMarks = totalSubStrandMarks + scoreValues.get(l);
							}

							System.out.println("The total substrandMarks is " + totalSubStrandMarks);
							System.out.println("The scoreValue size is " + scoreValues.size());

							int overalScorePerStrand;
							if (scoreValues.size() != 0) {

								overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

								strandValues.add(overalScorePerStrand);
							}

							else {
								overalScorePerStrand = 0;
							}

							strandValues.add(overalScorePerStrand);

						}
					}

					for (int k = 0; k < strandValues.size(); k++) {
						totalStrandMarks += strandValues.get(k);

					}

					System.out.println("The total marks is " + totalStrandMarks);

					System.out.println("The strandValue size is" + strandValues.size());

					overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

					int term = grade.getTerm();
					switch (term) {
					case 1:
						System.out.println("Overal term 1 is overalScorePerLearningArea");
						ResultRecord resultRecord = new ResultRecord();
						resultRecord.setLearner(learnerInGrade);
						resultRecord.setLearningAreaName(learningArea.getLearningAreaName());
						resultRecord.setOveralTermOneScore(overalScorePerLearningArea);
						solidarityService.saveResultRecord(resultRecord);

						break;
					case 2:
						System.out.println("Overal term 2 is overalScorePerLearningArea");
						ResultRecord resultRecordTwo = solidarityService
								.findResultRecordUsingLearnerIdAndLearningAreaName(learnerInGrade,
										learningArea.getLearningAreaName());
						if (resultRecordTwo == null) {
							ResultRecord resultRecordNewTwo = new ResultRecord();
							resultRecordNewTwo.setLearner(learnerInGrade);
							resultRecordNewTwo.setLearningAreaName(learningArea.getLearningAreaName());
							resultRecordNewTwo.setOveralTermTwoScore(overalScorePerLearningArea);
							solidarityService.saveResultRecord(resultRecordNewTwo);

						} else {
							resultRecordTwo.setOveralTermTwoScore(overalScorePerLearningArea);
							solidarityService.saveResultRecord(resultRecordTwo);
						}
						break;
					case 3:

						System.out.println("Overal term 3 is overalScorePerLearningArea");
						ResultRecord resultRecordThree = solidarityService
								.findResultRecordUsingLearnerIdAndLearningAreaName(learnerInGrade,
										learningArea.getLearningAreaName());

						if (resultRecordThree == null) {
							ResultRecord resultRecordNewThree = new ResultRecord();
							resultRecordNewThree.setLearner(learnerInGrade);
							resultRecordNewThree.setLearningAreaName(learningArea.getLearningAreaName());
							resultRecordNewThree.setOveralTermThreeScore(overalScorePerLearningArea);
							solidarityService.saveResultRecord(resultRecordNewThree);

						} else {

							resultRecordThree.setOveralTermThreeScore(overalScorePerLearningArea);
							solidarityService.saveResultRecord(resultRecordThree);

						}
						break;
					}

				}

			}

		}

		return "successPage";
	}

	// sending downloadFilterPage to the browser
	@RequestMapping("/downloadAcademicReport")
	public String showDowloadFilterPage() {
		return "downloadAcademicReportFilterPage";
	}

	// generating the pdf academic report
	@RequestMapping("/generateAcademicReport")
	public void generateAcademicReport(@RequestParam("learnerId") int learnerId, HttpServletResponse response)
			throws DocumentException, IOException {
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		List<LearningArea> learningAreas = learner.getLearning_areas();

		for (int i = 0; i < learningAreas.size(); i++) {
			LearningArea learningArea = learningAreas.get(i);
			System.out.println("The rubric :" + learningArea.getRubric());
		}
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String termDetails = "_term" + learner.getGrade().getTerm();
		String headerValue = "attachment; filename=" + learner.getFirstName() + "_" + learner.getSecondName() + "_"
				+ learner.getSurName() + termDetails + ".pdf";
		response.setHeader(headerKey, headerValue);

		PdfGenerator pdfGenerator = new PdfGenerator();
		pdfGenerator.setLearner(learner);
		pdfGenerator.setLearningAreas(learningAreas);
		pdfGenerator.export(response, learner.getGrade().getTerm());
	}

	// sending nextGradePage to the browser
	@RequestMapping("/nextGradeRequest")
	public String showNextGradeForm() {
		return "nextGradePage";
	}

	// confirm next grade request
	@RequestMapping("/confirmNextGradeRequest")
	public String confirmNextGrade(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,
			ModelMap modelMap) {
		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade gradeOld = classTeacher.getGrade();

		if (gradeOld.getTerm() == 3 && gradeOld.getTermEnded() == true) {
			gradeOld.setFinalClassTeacherId(classTeacher.getClassTeacherId());
			ClassTeacher dummyClassTeacher = new ClassTeacher();
			dummyClassTeacher.setClassTeacherId(0);
			dummyClassTeacher.setSchool(gradeOld.getSchool());
			solidarityService.saveClassTeacher(dummyClassTeacher);

			Grade gradeNew = new Grade();
			gradeOld.setClass_teacher(dummyClassTeacher);
			gradeNew.setSchool(gradeOld.getSchool());
			gradeNew.setClass_teacher(classTeacher);
			gradeNew.setTerm(1);
			gradeNew.setTermEnded(false);
			solidarityService.saveGrade(gradeNew);
			System.out.println("Subject teachers empty" + gradeOld.getSubject_teachers().isEmpty());

			List<Learner> oldGradeLearners = gradeOld.getLearners();

			for (int i = 0; i < oldGradeLearners.size(); i++) {
				Learner oldGradeLearner = oldGradeLearners.get(i);

				Learner newGradeLearner = new Learner();

				newGradeLearner.setFirstName(oldGradeLearner.getFirstName());
				newGradeLearner.setGrade(gradeNew);
				newGradeLearner.setNemissNumber(oldGradeLearner.getNemissNumber());
				newGradeLearner.setSchool(oldGradeLearner.getSchool());
				newGradeLearner.setSecondName(oldGradeLearner.getSecondName());
				newGradeLearner.setSurName(oldGradeLearner.getSurName());
				solidarityService.saveLearner(newGradeLearner);
				gradeNew.getLearners().add(newGradeLearner);

			}

			List<LearningArea> oldLearningAreas = gradeOld.getLearning_areas();
			List<LearningArea> newLearningAreas = new ArrayList<LearningArea>();

			for (int i = 0; i < oldLearningAreas.size(); i++) {
				LearningArea oldLearningArea = oldLearningAreas.get(i);

				LearningArea newLearningArea = new LearningArea();

				newLearningArea.setGrade(gradeNew);
				newLearningArea.setLearningAreaName(oldLearningArea.getLearningAreaName());
				solidarityService.saveLearningArea(newLearningArea);

				newLearningAreas.add(newLearningArea);

			}

			gradeNew.setLearning_areas(newLearningAreas);
			gradeNew.setTerm(1);
			gradeNew.setGradeNumber(gradeOld.getGradeNumber() + 1);
			gradeNew.setYear(gradeOld.getYear() + 1);
			gradeNew.setGradeStream(gradeOld.getGradeStream());
			
			Grade grade = generateGradeDisplayName.createDisplayName(gradeNew);
			

			solidarityService.saveGrade(gradeOld);
			solidarityService.saveGrade(grade);

			return "successPage";
		} else {
			modelMap.addAttribute("msg", "Term 3 has not yet ended please wait for end of academic year !");
			return "nextGradePage";
		}

	}

	// sending the filter items to the cookie
	@RequestMapping("/submitFilter")
	public String processDownloadFilter(@RequestParam("year") int year, @RequestParam("termNumber") int termNumber,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,
			ModelMap modelMap, HttpServletResponse response) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();
		School school = grade.getSchool();

		if (school.getPaid() == null) {
			modelMap.addAttribute("msg",
					"Termly payment has not been made by Head teacher ensure it has been paid to download");
			return "downloadAcademicReportFilterPage";
		}

		else if (school.getPaid() == false) {
			modelMap.addAttribute("msg",
					"Termly payment has not been made by Head teacher ensure it has been paid to download");
			return "downloadAcademicReportFilterPage";
		}

		else if (grade.getYear() == year) {

			if (termNumber == grade.getTerm()) {
				modelMap.addAttribute("msg",
						"Term has not ended yet please view current learner progress in the home dashboard");
				return "downloadAcademicReportFilterPage";
			} else if (termNumber > grade.getTerm()) {

				String message = "Academic report for term " + termNumber + "not yet available";
				modelMap.addAttribute("msg", message);
			}

			else if (termNumber < grade.getTerm()) {
				Cookie cookie = new Cookie("termNumber", String.valueOf(termNumber));
				Cookie cookieTwo = new Cookie("year", String.valueOf(year));
				response.addCookie(cookie);
				response.addCookie(cookieTwo);
				List<Learner> learnersInGrade = grade.getLearners();
				modelMap.addAttribute("learners", learnersInGrade);
				modelMap.addAttribute("grade", grade);
				modelMap.addAttribute("term", termNumber);
				return "downloadWithLearners";
			}

		}

		else if (grade.getYear() > year) {
			int range = grade.getYear() - year;

			int pastGradeNumber = grade.getGradeNumber() - range;

			Grade oldGrade = solidarityService.findGradeUsingNumberStreamSchoolYear(pastGradeNumber,
					grade.getGradeStream(), grade.getSchool(), year);

			if (oldGrade == null) {
				modelMap.addAttribute("msg", "Grade not available !");
				return "downloadAcademicReportFilterPage";
			} else {

				List<Learner> learnersInGrade = oldGrade.getLearners();

				modelMap.addAttribute("learners", learnersInGrade);
				modelMap.addAttribute("term", termNumber);
				modelMap.addAttribute("grade", oldGrade);

				Cookie cookie = new Cookie("termNumber", String.valueOf(termNumber));

				response.addCookie(cookie);

				return "downloadWithLearners";
			}

		}

		else if (grade.getYear() < year) {
			modelMap.addAttribute("msg", "Academic reports for the year: " + year + " not yet available !");
			return "downloadAcademicReportFilterPage";
		}

		return "downloadAcademicReportFilterPage";
	}

	// downloading old academic report
	@RequestMapping("/downloadReport")
	public void downloadReport(@RequestParam("learnerId") int learnerId,
			@CookieValue(value = "termNumber", defaultValue = "000") int termNumber, HttpServletResponse response)
			throws DocumentException, IOException {
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);

		List<ResultRecord> resultRecords = learner.getResultRecords();

		List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

		List<Integer> performanceList = new ArrayList<Integer>();

		for (int i = 0; i < resultRecords.size(); i++) {
			ResultRecord resultRecord = resultRecords.get(i);
			LearningArea learningArea = new LearningArea();
			learningArea.setLearningAreaName(resultRecord.getLearningAreaName());
			if (termNumber == 1) {
				learningArea.setOveralTermOneScore(resultRecord.getOveralTermOneScore());

				performanceList.add(resultRecord.getOveralTermOneScore());

				switch (learningArea.getOveralTermOneScore()) {
				case 0:
					learningArea.setRubric("none");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 1:
					learningArea.setRubric("B.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 2:
					learningArea.setRubric("A.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 3:
					learningArea.setRubric("M.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 4:
					learningArea.setRubric("E.E");
					evaluatedLearningAreas.add(learningArea);
					break;
				}
			}

			else if (termNumber == 2) {
				performanceList.add(resultRecord.getOveralTermTwoScore());
				switch (learningArea.getOveralTermTwoScore()) {
				case 0:
					learningArea.setRubric("none");
					evaluatedLearningAreas.add(learningArea);
					break;
				case 1:
					learningArea.setRubric("B.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 2:
					learningArea.setRubric("A.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 3:
					learningArea.setRubric("M.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 4:
					learningArea.setRubric("E.E");
					evaluatedLearningAreas.add(learningArea);
					break;
				}
			} else if (termNumber == 3) {
				performanceList.add(resultRecord.getOveralTermThreeScore());
				switch (learningArea.getOveralTermThreeScore()) {
				case 0:
					learningArea.setRubric("none");
					evaluatedLearningAreas.add(learningArea);
					break;
				case 1:
					learningArea.setRubric("B.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 2:
					learningArea.setRubric("A.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 3:
					learningArea.setRubric("M.E");
					evaluatedLearningAreas.add(learningArea);
					break;

				case 4:
					learningArea.setRubric("E.E");
					evaluatedLearningAreas.add(learningArea);
					break;
				}
			}
		}

		int sumOfResultRecords = 0;

		for (int i = 0; i < performanceList.size(); i++) {
			sumOfResultRecords += performanceList.get(i);
		}

		int overalPoint = Math.round(sumOfResultRecords / performanceList.size());
		learner.setOveralPoint(overalPoint);
		System.out.println("The overals is" + overalPoint);

		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String termDetails = "_term" + termNumber;
		String headerValue = "attachment; filename=" + learner.getFirstName() + "_" + learner.getSecondName() + "_"
				+ learner.getSurName() + termDetails + ".pdf";
		response.setHeader(headerKey, headerValue);

		PdfGenerator pdfGenerator = new PdfGenerator();
		pdfGenerator.setLearner(learner);
		pdfGenerator.setLearningAreas(evaluatedLearningAreas);
		pdfGenerator.export(response, termNumber);
	}

	// generating grade details for the headTeacher
	@RequestMapping("/gradeDetails")
	public String gradeDetails(@RequestParam("gradeId") int gradeId, ModelMap modelMap) {
		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);

		List<Learner> learnersInGrade = grade.getLearners();
		List<SubjectTeacher> subjectTeachersInGrade = grade.getSubject_teachers();
		List<LearningArea> learningAreasInGrade = grade.getLearning_areas();

		String path = servletContext.getRealPath("/");
		List<Data> data = new ArrayList<Data>();

		if (grade.getTerm() == null) {
			Data dataTwo = new Data();
			dataTwo.setScore(0);
			dataTwo.setTerm("One");
			data.add(dataTwo);

			generateBarGraph.drawBarGraph(path, data);

		}

		else if (grade.getTerm() == 1) {
			Data dataTwo = new Data();
			dataTwo.setScore(calculateResults.generateOveralGradeMinScore(grade, 1));
			dataTwo.setTerm("One");
			data.add(dataTwo);

			generateBarGraph.drawBarGraph(path, data);

		}

		else if (grade.getTerm() == 2) {
			Grade gradeOne = solidarityService.findGradeUsingNumberStreamSchoolYear(grade.getGradeNumber() - 1,
					grade.getGradeStream(), grade.getSchool(), grade.getYear() - 1);
			Data dataOne = new Data();
			dataOne.setScore(calculateResults.generateOveralGradeMinScore(gradeOne, 3));
			dataOne.setTerm("Three");
			data.add(dataOne);

			Data dataTwo = new Data();
			dataTwo.setScore(calculateResults.generateOveralGradeMinScore(grade, 1));
			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(calculateResults.generateOveralGradeMinScore(grade, 2));
			dataTwo.setTerm("Two");
			data.add(dataThree);

			generateBarGraph.drawBarGraph(path, data);
		}

		else if (grade.getTerm() == 3) {

			Data dataTwo = new Data();
			dataTwo.setScore(calculateResults.generateOveralGradeMinScore(grade, 1));

			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(calculateResults.generateOveralGradeMinScore(grade, 2));
			dataThree.setTerm("Two");
			data.add(dataThree);

			Data dataFour = new Data();
			dataFour.setScore(calculateResults.generateOveralGradeMinScore(grade, 3));

			dataFour.setScore(4);
			dataFour.setTerm("Three");
			data.add(dataFour);

			generateBarGraph.drawBarGraph(path, data);

		}

		modelMap.addAttribute("learnersNumber", learnersInGrade.size());
		modelMap.addAttribute("subjectTeacherNumber", subjectTeachersInGrade.size());
		modelMap.addAttribute("learningAreaNumber", learningAreasInGrade.size());
		modelMap.addAttribute("learners", learnersInGrade);
		modelMap.addAttribute("learningAreas", learningAreasInGrade);
		modelMap.addAttribute("grade", grade);

		System.out.println("The overal grade meanScore is: " + calculateResults.generateOveralGradeMinScore(grade, 3));

		return "gradeDetailsPage";
	}

	// generating subject Teacher details for the headteacher
	@RequestMapping("/teacherDetails")
	public String teacherDetails(@RequestParam("teacherId") int teacherId, ModelMap modelMap) {
		SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(teacherId);
		List<Grade> gradesTaught = subjectTeacher.getGrades();
		List<LearningArea> learningAreasTaught = subjectTeacher.getLearning_areas();

		modelMap.addAttribute("grades", gradesTaught);
		modelMap.addAttribute("learningAreas", learningAreasTaught);
		modelMap.addAttribute("subjectTeacher", subjectTeacher);
		modelMap.addAttribute("gradeNumber", gradesTaught.size());
		modelMap.addAttribute("learningAreaNumber", learningAreasTaught.size());

		return "teacherDetailsPage";
	}

	// showing list of learners in a taught grade for subject teacher
	@RequestMapping("/viewLearners")
	public String showLearnersInGrade(@RequestParam("learningAreaId") int learningAreaId,
			@RequestParam("gradeId") int gradeId, ModelMap modelMap, HttpServletResponse response) {

		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);
		List<Learner> learners = grade.getLearners();
		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);

		Cookie cookie = new Cookie("learningAreaId", String.valueOf(learningAreaId));
		response.addCookie(cookie);
		return "learnersListPage";
	}

	// showing the learner's individual performance per learning area and generating
	@RequestMapping("/showLearnersPerformance")
	public String performancePerLearningArea(@RequestParam("learnerId") int learnerId,
			@CookieValue(value = "learningAreaId", defaultValue = "000") int learningAreaId, ModelMap modelMap,
			HttpServletResponse response) {

		String path = servletContext.getRealPath("/");
		List<Data> data = new ArrayList<Data>();

		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));
		response.addCookie(cookie);
		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);
		Grade grade = learner.getGrade();

		System.out.println(calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));

		int term = grade.getTerm();

		switch (term) {

		case 1:
			Data dataOne = new Data();
			dataOne.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			dataOne.setTerm("One");
			data.add(dataOne);
			generateBarGraph.drawBarGraph(path, data);
			break;

		case 2:
			Data dataTwo = new Data();
			dataTwo.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 2, grade));
			dataThree.setTerm("Two");
			data.add(dataThree);
			generateBarGraph.drawBarGraph(path, data);

			break;

		case 3:

			Data datafour = new Data();
			datafour.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			datafour.setTerm("One");
			data.add(datafour);

			Data dataFive = new Data();
			dataFive.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 2, grade));
			dataFive.setTerm("Two");
			data.add(dataFive);

			Data dataSix = new Data();
			dataSix.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 3, grade));
			dataSix.setTerm("Three");
			data.add(dataSix);
			generateBarGraph.drawBarGraph(path, data);

			break;

		}
		modelMap.addAttribute("learner", learner);
		modelMap.addAttribute("learningArea", learningArea);

		return "learnerLearningAreaPerformancePage";

	}

	@RequestMapping("/showLearnersPerformanceDuplicate")
	public String performancePerLearningAreaDuplicate(@RequestParam("confirm") Boolean confirm,
			@CookieValue(value = "learnerId", defaultValue = "00") int learnerId,
			@CookieValue(value = "learningAreaId", defaultValue = "000") int learningAreaId, ModelMap modelMap,
			HttpServletResponse response) {

		String path = servletContext.getRealPath("/");
		List<Data> data = new ArrayList<Data>();

		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));
		response.addCookie(cookie);
		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);
		Grade grade = learner.getGrade();

		System.out.println(calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));

		int term = grade.getTerm();

		switch (term) {

		case 1:
			Data dataOne = new Data();
			dataOne.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			dataOne.setTerm("One");
			data.add(dataOne);
			generateBarGraph.drawBarGraph(path, data);
			break;

		case 2:
			Data dataTwo = new Data();
			dataTwo.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			dataTwo.setTerm("One");
			data.add(dataTwo);

			Data dataThree = new Data();
			dataThree.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 2, grade));
			dataThree.setTerm("Two");
			data.add(dataThree);
			generateBarGraph.drawBarGraph(path, data);

			break;

		case 3:

			Data datafour = new Data();
			datafour.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 1, grade));
			datafour.setTerm("One");
			data.add(datafour);

			Data dataFive = new Data();
			dataFive.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 2, grade));
			dataFive.setTerm("Two");
			data.add(dataFive);

			Data dataSix = new Data();
			dataSix.setScore(
					calculateResults.generateLearnerOveralScorePerLearningArea(learner, learningArea, 3, grade));
			dataSix.setTerm("Three");
			data.add(dataSix);
			generateBarGraph.drawBarGraph(path, data);

			break;

		}

		if (confirm == true) {
			modelMap.addAttribute("msgOne", "Strands not available yet!");
		}
		modelMap.addAttribute("learner", learner);
		modelMap.addAttribute("learningArea", learningArea);

		return "learnerLearningAreaPerformancePage";

	}

	/*
	 * //calculating overall grade performance per learning area and generating
	 * graphs this method will be revived
	 * 
	 * @RequestMapping("/gradePerformancePerLearningArea") public String
	 * calculateOveralGradePerformancePerLearningArea(@RequestParam("gradeId")int
	 * gradeId) { String path = servletContext.getRealPath("/"); List<Data> data =
	 * new ArrayList<Data>();
	 * 
	 * Grade grade = solidarityService.findGradeUsingGradeId(gradeId);
	 * 
	 * int term = grade.getTerm(); switch(term) {
	 * 
	 * case 1: Data dataOne = new Data();
	 * dataOne.setScore(calculateResults.generateLearnerOveralScorePerLearningArea(
	 * grade, learningArea, 1, grade)); dataOne.setTerm("One"); data.add(dataOne);
	 * generateBarGraph.drawBarGraph(path, data); break;
	 * 
	 * case 2: Data dataTwo = new Data();
	 * dataTwo.setScore(calculateResults.generateLearnerOveralScorePerLearningArea(
	 * grade, learningArea, 1, grade)); dataTwo.setTerm("One"); data.add(dataTwo);
	 * 
	 * 
	 * Data dataThree = new Data();
	 * dataThree.setScore(calculateResults.generateLearnerOveralScorePerLearningArea
	 * (grade, learningArea, 2, grade)); dataThree.setTerm("Two");
	 * data.add(dataThree); generateBarGraph.drawBarGraph(path, data);
	 * 
	 * break;
	 * 
	 * case 3:
	 * 
	 * Data datafour = new Data();
	 * datafour.setScore(calculateResults.generateLearnerOveralScorePerLearningArea(
	 * grade, learningArea, 1, grade)); datafour.setTerm("One"); data.add(datafour);
	 * 
	 * Data dataFive = new Data();
	 * dataFive.setScore(calculateResults.generateLearnerOveralScorePerLearningArea(
	 * grade, learningArea, 2, grade)); dataFive.setTerm("Two"); data.add(dataFive);
	 * 
	 * 
	 * Data dataSix = new Data();
	 * dataSix.setScore(calculateResults.generateLearnerOveralScorePerLearningArea(
	 * grade, learningArea, 3, grade)); dataSix.setTerm("Three"); data.add(dataSix);
	 * generateBarGraph.drawBarGraph(path, data);
	 * 
	 * break;
	 * 
	 * 
	 * 
	 * return"gradePerformancePerLearning"; }
	 */

	// returning the transfer learner page to browser
	@RequestMapping("/transferLearner")
	public String transferLearner(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String classTeacherPhoneNumber,
			ModelMap modelMap) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
		Grade grade = classTeacher.getGrade();

		List<Learner> learners = grade.getLearners();

		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);

		return "transferLearnerPage";
	}

	// verifying the transfer of the learner
	@RequestMapping("/verifyTransfer")
	public String verifyTransferLearner(@RequestParam("learnerId") int learnerId, ModelMap modelMap,
			HttpServletResponse response) {
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));
		response.addCookie(cookie);

		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		modelMap.addAttribute("learner", learner);

		return "confirmTransfer";

	}

	// changing the learner details
	@RequestMapping("/transferAction")
	public String transferAction(@CookieValue(value = "learnerId", defaultValue = "000") int learnerId) {
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);

		School school = solidarityService.findSchoolUsingName("dummy");

		Grade grade = solidarityService.findGradeUsingNumber(0, "dummy", school);

		learner.setSchool(school);

		learner.setGrade(grade);

		solidarityService.saveLearner(learner);

		return "successPage";
	}

	// Reconstructing the classTeacherFirstPage
	@RequestMapping("/classTeacherHomePage")
	public String classTeacherHomePage(
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String phoneNumber,
			ModelMap modelMap) {
		Grade grade = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber).getGrade();
		List<Learner> learners = solidarityService.findLearnerUsingGradeId(grade);
		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);
		modelMap.addAttribute("term", grade.getTerm());
		return "classTeacherFirstPage";
	}

	// sending acceptLearnerPage to browser
	@RequestMapping("/acceptLearner")
	public String acceptLearner() {
		return "acceptLearnerPage";
	}

	// verify and accepting learner
	@RequestMapping("/acceptLearnerAction")
	public String acceptLearnerAction(@RequestParam("nemissNumber") String nemissNumber, ModelMap modelMap,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "000") String phoneNumber) {
		List<Learner> learners = solidarityService.findLearnerUsingNemissNumber(nemissNumber);
		List<Learner> filteredLearners = new ArrayList<Learner>();
		for (int i = 0; i < learners.size(); i++) {
			Learner learner = learners.get(i);

			if (learner.getSchool().getSchoolName().equals("dummy") == true) {
				filteredLearners.add(learner);
			}

		}

		if (filteredLearners.isEmpty() == true) {
			modelMap.addAttribute("msg", "Learner has not been transfered from previous school");
			return "acceptLearnerPage";
		}

		else {
			Learner learner = filteredLearners.get(0);

			ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);

			School school = classTeacher.getSchool();
			Grade grade = classTeacher.getGrade();

			learner.setSchool(school);
			learner.setGrade(grade);

			solidarityService.saveLearner(learner);

			modelMap.addAttribute("learner", learner);

			return "confirmLearnerAcceptPage";
		}

	}

	// sending the payment options page to the browser with cost of payment
	@RequestMapping("/paymentOptions")
	public String paymentOptions(
			@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "000") String phoneNumber,
			ModelMap modelMap) {
		HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);

		School school = headTeacher.getSchool();

		int accountNumber = school.getSchoolId();

		List<Learner> learners = school.getLearners();
		List<String> learnersNames = new ArrayList<String>();
		
		for(int i=0;i<learners.size();i++)
		{
			Learner learner = learners.get(i);
			learnersNames.add(learner.getFirstName());
			
		}
		
		
		HashSet<String> actualLearnersNames = new HashSet<String>();
		actualLearnersNames.addAll(learnersNames);
		
		int numberOfLearners = actualLearnersNames.size();

		int amountPayable = numberOfLearners * 100;
		modelMap.addAttribute("accountNumber", accountNumber);
		modelMap.addAttribute("numberOfLearners", numberOfLearners);
		modelMap.addAttribute("amountPayable", amountPayable);

		return "paymentOptionsPage";
	}

	// sending the admin registration page to the browser
	@RequestMapping("/adminRegistration")
	public String adminRegistration() {
		return "adminRegistrationPage";
	}

	// saving the admin
	@RequestMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute("admin") Admin admin) {
		solidarityService.saveAdmin(admin);
		return "successPage";
	}

	// send the adminLoginForm to the browser
	@RequestMapping("/adminLogin")
	public String showAdminLoginForm() {
		return "adminLoginForm";
	}

	// verify the admin password
	@RequestMapping("/verifyAdmin")
	public String verifyAdmin(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {

		Admin admin = solidarityService.findAdminUsingPhoneNumber(phoneNumber);

		if (admin == null) {
			modelMap.addAttribute("msg", "admin not registered please register and Try again!");

			return "adminLoginForm";
		}

		else if (admin.getPassword().equals(password)) {

			Cookie cookie = new Cookie("adminPhoneNumber", admin.getPhone_number());

			response.addCookie(cookie);

			List<School> schools = solidarityService.findAllSchoolsEnrolled();
			List<Learner> learners = solidarityService.findAllLearnersEnrolled();
			
			List<String> learnersNames = new ArrayList<String>();
			
			for(int i=0;i<learners.size();i++)
			{
				Learner learner = learners.get(i);
				learnersNames.add(learner.getFirstName());
				
			}
			
			
			HashSet<String> actualLearnersNames = new HashSet<String>();
			actualLearnersNames.addAll(learnersNames);

			int schoolNumber = schools.size();
			int learnerNumber = actualLearnersNames.size();

			modelMap.addAttribute("admin", admin);
			modelMap.addAttribute("schools", schools);
			modelMap.addAttribute("schoolNumber", schoolNumber);
			modelMap.addAttribute("learnerNumber", learnerNumber);

			return "adminFirstPage";
		}

		else {
			modelMap.addAttribute("msg", "invalid password please try again!");

			System.out.println("The password from the db is: " + admin.getPassword());
			System.out.println("The password from the input is: " + password);

			return "adminLoginForm";
		}

	}

	// admin first page
	@RequestMapping("/verifySchoolPayment")
	public String verifySchoolPayment(@RequestParam("schoolId") int schoolId, ModelMap modelMap,
			@CookieValue(value = "adminPhoneNumber", defaultValue = "000") String adminPhoneNumber) {
		Admin admin = solidarityService.findAdminUsingPhoneNumber(adminPhoneNumber);

		System.out.println("The phone number" + adminPhoneNumber);
		School school = solidarityService.findSchoolUsingSchoolId(schoolId);

		school.setPaid(true);
		solidarityService.saveSchool(school);

		List<School> schools = solidarityService.findAllSchoolsEnrolled();
		List<Learner> learners = solidarityService.findAllLearnersEnrolled();
		
		List<String> learnersNames = new ArrayList<String>();
		
		for(int i=0;i<learners.size();i++)
		{
			Learner learner = learners.get(i);
			learnersNames.add(learner.getFirstName());
			
		}
		
		
		HashSet<String> actualLearnersNames = new HashSet<String>();
		actualLearnersNames.addAll(learnersNames);

		int schoolNumber = schools.size();
		int learnerNumber = actualLearnersNames.size();

		modelMap.addAttribute("admin", admin);
		modelMap.addAttribute("schools", schools);
		modelMap.addAttribute("schoolNumber", schoolNumber);
		modelMap.addAttribute("learnerNumber", learnerNumber);

		return "adminFirstPage";
	}

	// resetting all payments for all schools in the system
	@RequestMapping("/resetAllPayments")
	public String resetAllPayments(ModelMap modelMap,
			@CookieValue(value = "adminPhoneNumber", defaultValue = "000") String adminPhoneNumber) {
		Admin admin = solidarityService.findAdminUsingPhoneNumber(adminPhoneNumber);
		List<School> schools = solidarityService.findAllSchoolsEnrolled();
		for (int i = 0; i < schools.size(); i++) {
			School school = schools.get(i);
			school.setPaid(false);
			solidarityService.saveSchool(school);
		}

		List<Learner> learners = solidarityService.findAllLearnersEnrolled();
		
		List<String> learnersNames = new ArrayList<String>();
		
		for(int i=0;i<learners.size();i++)
		{
			Learner learner = learners.get(i);
			learnersNames.add(learner.getFirstName());
			
		}
		
		
		HashSet<String> actualLearnersNames = new HashSet<String>();
		actualLearnersNames.addAll(learnersNames);

		int schoolNumber = schools.size();
		int learnerNumber = actualLearnersNames.size();

		modelMap.addAttribute("admin", admin);
		modelMap.addAttribute("schools", schools);
		modelMap.addAttribute("schoolNumber", schoolNumber);
		modelMap.addAttribute("learnerNumber", learnerNumber);

		return "adminFirstPage";
	}

	// sending the headteacherRestorePage to the browser
	@RequestMapping("/headTeacherRestore")
	public String headTeacherRestore() {
		return "headTeacherRestorePage";
	}

	// returning the HeadTeacherNewPasswordPage to the browser
	@RequestMapping("/restoreHeadTeacherPasswordAction")
	public String restoreHeadTeacherPasswordAction(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("schoolCodeNumber") String schoolCodeNumber, ModelMap modelMap,
			HttpServletResponse response) {

		HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);

		if (headTeacher == null) {
			modelMap.addAttribute("msg", "Head Teacher not registered please contact customer care!");

			return "headTeacherRestorePage";
		}

		Cookie cookie = new Cookie("headTeacherPhoneNumber", headTeacher.getPhoneNumber());
		response.addCookie(cookie);
		School school = headTeacher.getSchool();

		if (school.getCodeNumber().equals(schoolCodeNumber)) {
			return "headTeacherNewPassword";

		}

		else {

			modelMap.addAttribute("msg", "School not registered please contact customer care!");

			return "headTeacherRestorePage";
		}

	}

	// changing the headTeacherPassword
	@RequestMapping("changeHeadTeacherPassword")
	public String changeHeadTeacherPassword(
			@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "000") String headTeacherPhoneNumber,
			@RequestParam("newPass") String newPassword, @RequestParam("passConfirm") String passConfirm,
			ModelMap modelMap) {

		if (newPassword.equals(passConfirm) == true) {

			HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(headTeacherPhoneNumber);

			headTeacher.setPassword(newPassword);

			solidarityService.saveHeadTeacher(headTeacher);

			return "successPage";
		} else {
			modelMap.addAttribute("msg", "password should be equal!");
			return "headTeacherNewPassword";
		}
	}

	// sending the class Teacher restore password page
	@RequestMapping("/classTeacherRestore")
	public String sendClassTeacherRestorePage() {
		return "classTeacherRestorePage";
	}

	// verifying the class teacher details
	@RequestMapping("/verifyRestoreClassTeacher")
	public String verifyRestorePassword(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("schoolCodeNumber") String schoolCodeNumber, HttpServletResponse response,
			ModelMap modelMap) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);

		if (classTeacher == null) {
			modelMap.addAttribute("msg", "Class teacher not registered please contact the customer care!");
			return "classTeacherRestorePage";
		}
		Cookie cookie = new Cookie("classTeacherId", String.valueOf(classTeacher.getClassTeacherId()));
		response.addCookie(cookie);

		if (classTeacher.getSchool().getCodeNumber().equals(schoolCodeNumber) == true) {
			return "classTeacherNewPasswordPage";
		} else {

			modelMap.addAttribute("msg", "school not registered  please contact the customer care");
			return "classTeacherRestorePage";
		}

	}

	// changing the ClassTeacher Password
	@RequestMapping("/changeClassTeacherPassword")
	public String changeClassTeacherPassword(ModelMap modelMap, @RequestParam("newPass") String newPass,
			@RequestParam("passConfirm") String passConfirn,
			@CookieValue(value = "classTeacherId", defaultValue = "000") int classTeacherId) {
		if (newPass.equals(passConfirn) == true) {
			ClassTeacher classTeacher = solidarityService.findClassTeacherUsingClassTeacherId(classTeacherId);

			classTeacher.setPassword(newPass);

			solidarityService.saveClassTeacher(classTeacher);

			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "password should be equal!");
			return "classTeacherNewPasswordPage";

		}

	}

//sending the subject Teacher restore password page
	@RequestMapping("/subjectTeacherRestore")
	public String sendSubjectTeacherRestorePage() {
		return "subjectTeacherRestorePage";
	}

	// verifying the subject teacher details
	@RequestMapping("/verifyRestoreSubjectTeacher")
	public String verifySubjectTeacherRestorePassword(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("schoolCodeNumber") String schoolCodeNumber, HttpServletResponse response,
			ModelMap modelMap) {

		SubjectTeacher subjectTeacher = solidarityService.findSubjectTeacherUsingPhoneNumber(phoneNumber);

		if (subjectTeacher == null) {
			modelMap.addAttribute("msg", "Subject teacher not registered please contact the customer care!");
			return "subjectTeacherRestorePage";
		}
		Cookie cookie = new Cookie("subjectTeacherId", String.valueOf(subjectTeacher.getSubjectTeacherId()));
		response.addCookie(cookie);

		if (subjectTeacher.getSchool().getCodeNumber().equals(schoolCodeNumber) == true) {
			return "subjectTeacherNewPasswordPage";
		} else {

			modelMap.addAttribute("msg", "school not registered  please contact the customer care");
			return "subjectTeacherRestorePage";
		}

	}

	// changing the subjectTeacher Password
	@RequestMapping("/changeSubjectTeacherPassword")
	public String changeSubjectTeacherPassword(ModelMap modelMap, @RequestParam("newPass") String newPass,
			@RequestParam("passConfirm") String passConfirn,
			@CookieValue(value = "subjectTeacherId", defaultValue = "000") int subjectTeacherId) {
		if (newPass.equals(passConfirn) == true) {

			SubjectTeacher subjectTeacher = solidarityService.findUsingSubjectTeacherId(subjectTeacherId);

			subjectTeacher.setPassword(newPass);

			solidarityService.saveSubjectTeacher(subjectTeacher);

			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "password should be equal!");
			return "subjectTeacherNewPasswordPage";

		}

	}

   //sending the enroller restore password page
	@RequestMapping("/enrollerRestore")
	public String sendEnrollerRestorePage() {
		return "enrollerRestorePage";
	}

	// verifying the enroller details
	@RequestMapping("/verifyRestoreEnroller")
	public String verifyEnrollerRestorePassword(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("nationalIdNumber") String nationalIdNumber, HttpServletResponse response,
			ModelMap modelMap) {

		Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(phoneNumber);

		if (enroller == null) {
			modelMap.addAttribute("msg", "Enroller not registered please contact the customer care!");
			return "enrollerRestorePage";
		}
		Cookie cookie = new Cookie("enrollerPhoneNumber", enroller.getPhoneNumber());
		response.addCookie(cookie);

		if (enroller.getIdNumber().equals(nationalIdNumber) == true) {
			return "enrollerNewPasswordPage";
		} else {

			modelMap.addAttribute("msg", "Invalid national Id number  please contact the customer care");
			return "enrollerRestorePage";
		}

	}

	// changing the enroller Password
	@RequestMapping("/changeEnrollerPassword")
	public String changeEnrollerPassword(ModelMap modelMap, @RequestParam("newPass") String newPass,
			@RequestParam("passConfirm") String passConfirn,
			@CookieValue(value = "enrollerPhoneNumber", defaultValue = "000") String phoneNumber) {
		if (newPass.equals(passConfirn) == true) {

			Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(phoneNumber);

			enroller.setPassword(newPass);

			solidarityService.saveEnroller(enroller);

			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "password should be equal!");
			return "enrollerNewPasswordPage";

		}

	}
	
	//sending the data specialist page to the browser
	@RequestMapping("/dataSpecialistRestore")
	public String sendDataSpecialistRestorePage() {
		return "dataSpecialistRestorePage";
	}
	
	// verifying the data specialist details
		@RequestMapping("/verifyRestoreDataSpecialist")
		public String verifyDataSpecialistRestorePassword(@RequestParam("phoneNumber") String phoneNumber,
				@RequestParam("nationalIdNumber") String nationalIdNumber, HttpServletResponse response,
				ModelMap modelMap) {

			
			DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);
			

			if (dataSpecialist == null) {
				modelMap.addAttribute("msg", "Data specialist not registered please contact the customer care!");
				return "dataSpecialistRestorePage";
			}
			Cookie cookie = new Cookie("dataSpecialistPhoneNumber", dataSpecialist.getPhoneNumber());
			response.addCookie(cookie);

			if (dataSpecialist.getIdNumber().equals(nationalIdNumber) == true) {
				return "dataSpecialistNewPasswordPage";
			} else {

				modelMap.addAttribute("msg", "Invalid national Id number  please contact the customer care");
				return "dataSpecialistRestorePage";
			}

		}
		

		// changing the data specialist Password
		@RequestMapping("/changeDataSpecialistPassword")
		public String changeDataSPecialistPassword(ModelMap modelMap, @RequestParam("newPass") String newPass,
				@RequestParam("passConfirm") String passConfirn,
				@CookieValue(value = "dataSpecialistPhoneNumber", defaultValue = "000") String phoneNumber) {
			if (newPass.equals(passConfirn) == true) {

				
				
				DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);

				dataSpecialist.setPassword(newPass);

				solidarityService.saveDataSpecialist(dataSpecialist);

				return "successPage";

			}

			else {

				modelMap.addAttribute("msg", "password should be equal!");
				return "dataSpecialistNewPasswordPage";

			}

		}
		
	
	
	
	
	
	
	

	// class returns classTeacher change phoneNumber page
	@RequestMapping("/changeClassTeacherPhoneNumber")
	public String changeClassTeacherPhoneNumber(@RequestParam("phoneNumber") String phoneNumber,
			HttpServletResponse response) {

		Cookie cookie = new Cookie("classTeacherPhoneNumber", phoneNumber);
		response.addCookie(cookie);
		return "changeClassTeacherPhoneNumberPage";
	}

	// change the class teacher phone number
	@RequestMapping("/changeClassTeacherPhoneNumberAction")
	public String changeClassTeacherPhoneNumberAction(ModelMap modelMap,
			@RequestParam("phoneNumber") String phoneNumber,
			@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "0000") String classTeacherPhoneNumber) {

		ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);

		if (classTeacher == null) {
			ClassTeacher classTeacherMain = solidarityService.findClassTeacherUsingPhoneNumber(classTeacherPhoneNumber);
			classTeacherMain.setPhoneNumber(phoneNumber);

			solidarityService.saveClassTeacher(classTeacherMain);
			
			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "phone number is already taken try another phone number");
			return "changeClassTeacherPhoneNumberPage";
		}

	}

 //class returns subjectTeacher change phoneNumber page
	@RequestMapping("/changeSubjectTeacherPhoneNumber")
	public String changeSubjectTeacherPhoneNumber(@RequestParam("phoneNumber") String phoneNumber,
			HttpServletResponse response) {

		Cookie cookie = new Cookie("subjectTeacherPhoneNumber", phoneNumber);
		response.addCookie(cookie);
		return "changeSubjectTeacherPhoneNumberPage";
	}

	// change the subject teacher phone number
	@RequestMapping("/changeSubjectTeacherPhoneNumberAction")
	public String changeSubjectTeacherPhoneNumberAction(ModelMap modelMap,
			@RequestParam("phoneNumber") String phoneNumber,
			@CookieValue(value = "subjectTeacherPhoneNumber", defaultValue = "0000") String subjectTeacherPhoneNumber) {

		SubjectTeacher subjectTeacher = solidarityService.findSubjectTeacherUsingPhoneNumber(phoneNumber);

		if (subjectTeacher == null) {
			SubjectTeacher subjectTeacherMain = solidarityService
					.findSubjectTeacherUsingPhoneNumber(subjectTeacherPhoneNumber);
			subjectTeacherMain.setPhoneNumber(phoneNumber);

			solidarityService.saveSubjectTeacher(subjectTeacherMain);
			
			
			
			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "phone number is already taken try another phone number");
			return "changeSubjectTeacherPhoneNumberPage";
		}

	}

	// class returns headTeacher change phoneNumber page
	@RequestMapping("/changeHeadTeacherPhoneNumber")
	public String changeHeadTeacherPhoneNumber(@RequestParam("phoneNumber") String phoneNumber,
			HttpServletResponse response) {

		Cookie cookie = new Cookie("headTeacherPhoneNumber", phoneNumber);
		response.addCookie(cookie);
		return "changeHeadTeacherPhoneNumberPage";
	}

	// change the head teacher phone number
	@RequestMapping("/changeHeadTeacherPhoneNumberAction")
	public String changeHeadTeacherPhoneNumberAction(ModelMap modelMap, @RequestParam("phoneNumber") String phoneNumber,
			@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "0000") String headTeacherPhoneNumber) {

		HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);

		if (headTeacher == null) {
			HeadTeacher headTeacherMain = solidarityService.findHeadTeacherUsingPhoneNumber(headTeacherPhoneNumber);
			headTeacherMain.setPhoneNumber(phoneNumber);

			solidarityService.saveHeadTeacher(headTeacherMain);
			return "successPage";

		}

		else {

			modelMap.addAttribute("msg", "phone number is already taken try another phone number");
			return "changeHeadTeacherPhoneNumberPage";
		}

	}

	// sending the data specialist registration to the browser
	@RequestMapping("/dataSpecialistRegistration")
	public String dataSpecialistRegistration() {
		return "dataSpecialistRegistrationPage";
	}

	// dataSpecialist saving to db
	@RequestMapping("/saveDataSpecialistRequest")
	public String saveDataSpecialistRequest(@ModelAttribute("dataSpecialist") DataSpecialist dataSpecialist,
			ModelMap modelMap) {
		if (solidarityService.findDataSpecialistUsingPhoneNumber(dataSpecialist.getPhoneNumber()) == null) {
			dataSpecialist.setApproved(false);

			solidarityService.saveDataSpecialist(dataSpecialist);

			return "successPage";
		}

		else {
			modelMap.addAttribute("msg", "Data Specialist already Enrolled please login!");
			return "dataSpecialistRegistrationPage";
		}
	}

	// sending dataSpecialistLoginPage to the browser
	@RequestMapping("/dataSpecialistLogin")
	public String dataSpecialistLogin() {
		return "dataSpecialistLoginPage";
	}

	// verifying data Specialist login request
	@RequestMapping("/dataSpecialistLoginVerification")
	public String dataSpecialistLoginVerification(@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password, ModelMap modelMap, HttpServletResponse response) {
		DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);

		if (dataSpecialist == null) {
			modelMap.addAttribute("msg", "Data specialist not Enrolled please Register!");
			return "dataSpecialistLoginPage";
		} else if (dataSpecialist.getPassword().equals(password) == true) {
			if (dataSpecialist.getApproved() == true) {
				modelMap.addAttribute("dataSpecialist", dataSpecialist);

				List<WorkSheet> workSheets = dataSpecialist.getWorkSheet();
				List<Integer> learnersId = new ArrayList<Integer>();

				for (int i = 0; i < workSheets.size(); i++) {
					WorkSheet workSheet = workSheets.get(i);
					learnersId.add(workSheet.getLearnerId());

				}

				HashSet<Integer> learnersIdentity = new HashSet<Integer>();
				learnersIdentity.addAll(learnersId);

				modelMap.addAttribute("learnerNumber", learnersIdentity.size());

				Cookie cookie = new Cookie("dataSpecialistPhoneNumber", dataSpecialist.getPhoneNumber());

				response.addCookie(cookie);
				return "dataSpecialistFirstPage";
			}

			else {
				return "waitingApprovalPage";
			}
		}

		else {
			modelMap.addAttribute("msg", "Invalid password please try again !");
			return "dataSpecialistLoginPage";
		}

	}
	
	
	// verifying data Specialist login request this is duplicate
	@RequestMapping("/dataSpecialistLoginVerificationDuplicate")
	public String dataSpecialistLoginVerificationDuplicate(@CookieValue(value="dataSpecialistPhoneNumber",defaultValue="00") String phoneNumber,
			 ModelMap modelMap, HttpServletResponse response) {
		DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);
	
				modelMap.addAttribute("dataSpecialist", dataSpecialist);

				List<WorkSheet> workSheets = dataSpecialist.getWorkSheet();
				List<Integer> learnersId = new ArrayList<Integer>();

				for (int i = 0; i < workSheets.size(); i++) {
					WorkSheet workSheet = workSheets.get(i);
					learnersId.add(workSheet.getLearnerId());

				}

				HashSet<Integer> learnersIdentity = new HashSet<Integer>();
				learnersIdentity.addAll(learnersId);

				modelMap.addAttribute("learnerNumber", learnersIdentity.size());

				Cookie cookie = new Cookie("dataSpecialistPhoneNumber", dataSpecialist.getPhoneNumber());

				response.addCookie(cookie);
				return "dataSpecialistFirstPage";
		
	       }
	
	

	// sending the data specialist profiles to admin
	@RequestMapping("/dataSpecialistProfiles")
	public String dataSpecialistProfiles(ModelMap modelMap) {
		List<DataSpecialist> dataSpecialists = solidarityService.findAllDataSpecialists();

		modelMap.addAttribute("dataSpecialists", dataSpecialists);

		return "dataSpecialistProfilesPage";
	}

	// approving the data specialist
	@RequestMapping("/approveDataSpecialist")
	public String approveDataSpecialist(@RequestParam("phoneNumber") String phoneNumber) {
		DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);
		dataSpecialist.setApproved(true);

		solidarityService.saveDataSpecialist(dataSpecialist);

		return "redirect:/dataSpecialistProfiles";
	}

	// disapproving dataSpecialists
	@RequestMapping("/dissapproveDataSpecialist")
	public String dissapproveDataSpecialist(@RequestParam("phoneNumber") String phoneNumber) {
		DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(phoneNumber);
		dataSpecialist.setApproved(false);

		solidarityService.saveDataSpecialist(dataSpecialist);

		return "redirect:/dataSpecialistProfiles";
	}

	// sending the searchSchoolPage to the browser
	@RequestMapping("/enterDataRequest")
	public String enterDataRequest() {

		return "searchSchoolPage";
	}

	// searching the school using the code number
	@RequestMapping("/searchSchool")
	public String searchSchool(@RequestParam("schoolCodeNumber") String SchoolCodeNumber, ModelMap modelMap,
			HttpServletResponse response) {
		School school = solidarityService.findUSingCodeNumber(SchoolCodeNumber);

		if (school == null) {
			modelMap.addAttribute("msg", "School not enrolled please try again Later!");
			return "searchSchoolPage";
		}

		else {

			modelMap.addAttribute("school", school);
			return "schoolDetailsPage";
		}

	}

	// Returning the grade list to the browser
	@RequestMapping("/confirmSchool")
	public String confirmSchool(@RequestParam("schoolId") int schoolId, ModelMap modelMap,HttpServletResponse response) {

		School school = solidarityService.findSchoolUsingSchoolId(schoolId);
		
		Cookie cookie = new Cookie("schoolId",String.valueOf(schoolId));
		response.addCookie(cookie);

		List<Grade> grades = school.getGrade();

		modelMap.addAttribute("grades", grades);
		modelMap.addAttribute("school", school);

		return "gradeListPage";
	}
	
	
	//duplicate method for confirmSchool above
	@RequestMapping("/confirmSchoolDuplicate")
	public String confirmSchoolDuplicate(@CookieValue(value="schoolId",defaultValue="00") int schoolId, ModelMap modelMap,HttpServletResponse response) {

		School school = solidarityService.findSchoolUsingSchoolId(schoolId);
		
		Cookie cookie = new Cookie("schoolId",String.valueOf(schoolId));
		response.addCookie(cookie);

		List<Grade> grades = school.getGrade();

		modelMap.addAttribute("grades", grades);
		modelMap.addAttribute("school", school);

		return "gradeListPage";
	}

	// creating the new grade by the data specialist
	@RequestMapping("/createNewGrade")
	public String createNewGrade(@RequestParam("schoolId") int schoolId, HttpServletResponse response) {
		Cookie cookie = new Cookie("schoolId", String.valueOf(schoolId));
		response.addCookie(cookie);
		return "newGradePage";
	}

	// saving the grade from the data specialist

	@RequestMapping("/saveNewGrade")
	public String saveNewGrade(@ModelAttribute("grade") Grade grade, ModelMap modelMap,
			@CookieValue("schoolId") int schoolId) {
		School school = solidarityService.findSchoolUsingSchoolId(schoolId);

		Grade gradeFromDb = solidarityService.findGradeUsingNumberStreamSchoolYear(grade.getGradeNumber(),
				grade.getGradeStream(), school, grade.getYear());

		if (gradeFromDb == null) {
			ClassTeacher dummyClassTeacher = new ClassTeacher();
			dummyClassTeacher.setSchool(school);

			solidarityService.saveClassTeacher(dummyClassTeacher);

			grade.setSchool(school);

			String stream = grade.getGradeStream();

			grade.setGradeStream(stream.toUpperCase());

			grade.setClass_teacher(dummyClassTeacher);

			solidarityService.saveGrade(grade);

			return "redirect:/enterDataRequest";

		}

		else {
			modelMap.addAttribute("msg", "Grade already enrolled!");
			return "newGradePage";
		}

	}

	// showing the number of learners in the grade for data specialist
	@RequestMapping("/selectGrade")
	public String selectGrade(@RequestParam("gradeId") int gradeId, ModelMap modelMap, HttpServletResponse response) {
		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);

		Cookie cookie = new Cookie("gradeId", String.valueOf(gradeId));
		response.addCookie(cookie);

		List<Learner> learners = grade.getLearners();

		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);
		modelMap.addAttribute("learnerNumber", learners.size());

		return "learnerListPageDs";
	}

	// replicate of the above method but using cookies for refesh purpose
	@RequestMapping("/selectGradeReplica")
	public String selectGradeReplica(@CookieValue(value = "gradeId", defaultValue = "00") int gradeId,
			ModelMap modelMap) {
		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);

		List<Learner> learners = grade.getLearners();

		modelMap.addAttribute("learners", learners);
		modelMap.addAttribute("grade", grade);
		modelMap.addAttribute("learnerNumber", learners.size());

		return "learnerListPageDs";
	}

	// return the create new learner page to the browser
	@RequestMapping("/createNewLearnerDs")
	public String createNewLearnerDs() {
		return "createNewLearnerPageDs";
	}

	// saving the learner from data specialist
	@RequestMapping("/saveNewLearnerDs")
	public String saveNewLearnerDs(@ModelAttribute("learner") Learner learner, ModelMap modelMap,
			@CookieValue(value = "gradeId", defaultValue = "00") int gradeId) {

		String nemissNumber = learner.getNemissNumber();

		Grade grade = solidarityService.findGradeUsingGradeId(gradeId);

		List<Learner> learnersInGrade = grade.getLearners();
		List<Learner> filteredLearners = new ArrayList<Learner>();

		for (int i = 0; i < learnersInGrade.size(); i++) {

			Learner learnerInGrade = learnersInGrade.get(i);

			if (learnerInGrade.getNemissNumber().equals(nemissNumber) == true) {
				filteredLearners.add(learnerInGrade);
			}

		}

		if (filteredLearners.isEmpty() == true) {

			learner.setGrade(grade);
			learner.setSchool(grade.getSchool());
			learner.setOveralPoint(0);

			solidarityService.saveLearner(learner);

			return "redirect:/selectGradeReplica";

		}

		else {
			modelMap.addAttribute("msg", "Learner already enrolled !");
			return "createNewLearnerPageDs";
		}

	}

	// sending the enter result for to the browser
	@RequestMapping("/enterResult")
	public String enterResult(@RequestParam("learnerId") int learnerId, HttpServletResponse response) {
		Cookie cookie = new Cookie("learnerId", String.valueOf(learnerId));

		response.addCookie(cookie);

		return "enterResultPage";

	}

	// saving the result from the data specialist and creating the workSheet
	@RequestMapping("/saveLearningAreaDs")
	public String saveLearningAreaDs(@RequestParam("learningAreaName") String learningAreaName,
			@RequestParam("termNumber") int termNumber, @RequestParam("scoreValue") int scoreValue, ModelMap modelMap,
			@CookieValue(value = "learnerId", defaultValue = "000") int learnerId,
			@CookieValue(value = "dataSpecialistPhoneNumber", defaultValue = "00") String dataSpecialistPhoneNumber) {

		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);
		DataSpecialist dataSpecialist = solidarityService.findDataSpecialistUsingPhoneNumber(dataSpecialistPhoneNumber);

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();

		WorkSheet workSheet = new WorkSheet();
		workSheet.setDataSpecialist(dataSpecialist);
		workSheet.setLearnerId(learnerId);
		workSheet.setMonth(month);
		workSheet.setLearningAreaName(learningAreaName.toUpperCase());
		workSheet.setYear(year);

		solidarityService.saveWorkSheet(workSheet);

		Grade grade = learner.getGrade();

		switch (termNumber) {
		case 1:
			grade.setBeenTermOne(true);
			grade.setTerm(1);
			solidarityService.saveGrade(grade);
			ResultRecord resultRecordOne = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(learner,
					learningAreaName.toUpperCase());
			if (resultRecordOne == null) {
				ResultRecord resultOne = new ResultRecord();
				resultOne.setLearner(learner);
				resultOne.setOveralTermOneScore(scoreValue);
				resultOne.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultOne);
				modelMap.addAttribute("msg", learningAreaName.toUpperCase() + " for term 1 saved successfully! ");
			} else {

				resultRecordOne.setOveralTermOneScore(scoreValue);
				resultRecordOne.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultRecordOne);
				modelMap.addAttribute("msg", learningAreaName.toUpperCase() + " for term 1 saved successfully!  ");
			}

			break;
		case 2:
			grade.setBeenTermTwo(true);
			grade.setTerm(2);
			solidarityService.saveGrade(grade);
			ResultRecord resultRecordTwo = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(learner,
					learningAreaName.toUpperCase());
			if (resultRecordTwo == null) {
				ResultRecord resultTwo = new ResultRecord();
				resultTwo.setLearner(learner);
				resultTwo.setOveralTermTwoScore(scoreValue);
				resultTwo.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultTwo);
				modelMap.addAttribute("msg",
						learningAreaName.toUpperCase() + " for term 2 saved successfully! Missing term 1 ");
			} else {
				resultRecordTwo.setOveralTermTwoScore(scoreValue);
				resultRecordTwo.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultRecordTwo);
				modelMap.addAttribute("msg", learningAreaName.toUpperCase() + " for term 2 saved  successfully!");
			}

			break;
		case 3:
			grade.setBeenTermThree(true);
			grade.setTerm(3);
			solidarityService.saveGrade(grade);

			ResultRecord resultRecordThree = solidarityService
					.findResultRecordUsingLearnerIdAndLearningAreaName(learner, learningAreaName.toUpperCase());
			if (resultRecordThree == null) {
				ResultRecord resultThree = new ResultRecord();
				resultThree.setLearner(learner);
				resultThree.setOveralTermThreeScore(scoreValue);
				resultThree.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultThree);
				modelMap.addAttribute("msg",
						learningAreaName.toUpperCase() + " for term 3 saved successfully! Missing term 2 ");
			} else {
				resultRecordThree.setOveralTermThreeScore(scoreValue);
				resultRecordThree.setLearningAreaName(learningAreaName.toUpperCase());
				solidarityService.saveResultRecord(resultRecordThree);
				modelMap.addAttribute("msg", learningAreaName.toUpperCase() + " for term 3 saved  successfully!");
			}

			break;

		}

		return "enterResultPage";
	}

	// returning all the strand of a given learning area
	public List<Strand> returnAllStrands(int LearningAreaId) {

		LearningArea learningArea = solidarityService.findLearningAreaUsingId(LearningAreaId);

		List<Strand> strands = learningArea.getStrands();

		return strands;
	}

	// returning all the sub strands of a given strand
	public List<Score> returnScores(int strandId, Learner learner) {
		Strand strand = solidarityService.findStrandUsingStrandId(strandId);

		List<SubStrand> subStrands = strand.getSub_strands();

		List<Score> scores = new ArrayList<Score>();

		for (int i = 0; i < subStrands.size(); i++) {
			SubStrand subStrand = subStrands.get(i);
			Score score = solidarityService.findScoreUsingLearnerIdAndSubStrand(learner, subStrand);
			scores.add(score);
		}

		return scores;
	}

	// sending the strand page to the subject teacher with the strands
	@RequestMapping("/showStrandSubjectTeacher")
	public String showStrands(@RequestParam("learningAreaId") int learningAreaId, ModelMap modelMap,HttpServletResponse response) {
		List<Strand> strands = returnAllStrands(learningAreaId);

		Cookie cookie = new Cookie("learningAreaId",String.valueOf(learningAreaId));
		response.addCookie(cookie);
		
		
		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

		if (strands.isEmpty() == true) {

			return "redirect:/showLearnersPerformanceDuplicate?confirm=true";
		}

		modelMap.addAttribute("strands", strands);
		modelMap.addAttribute("learningAreaName", learningArea.getLearningAreaName());

		return "subjectTeacherStrandPage";
	}
	
	
	// sending the strand page to the subject teacher with the strands duplicate
		@RequestMapping("/showStrandSubjectTeacherDuplicate")
		public String showStrandsDuplicate(@CookieValue(value="learningAreaId",defaultValue="00") int learningAreaId, ModelMap modelMap,HttpServletResponse response) {
			List<Strand> strands = returnAllStrands(learningAreaId);

			Cookie cookie = new Cookie("learningAreaId",String.valueOf(learningAreaId));
			response.addCookie(cookie);
			
			
			LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

			if (strands.isEmpty() == true) {

				return "redirect:/showLearnersPerformanceDuplicate?confirm=true";
			}

			modelMap.addAttribute("strands", strands);
			modelMap.addAttribute("learningAreaName", learningArea.getLearningAreaName());

			return "subjectTeacherStrandPage";
		}
	

	// sending the scores to the subject teacher per substrand
	@RequestMapping("/showScoresSubjectTeacher")
	public String showScores(@RequestParam("strandId") int strandId, ModelMap modelMap,
			@CookieValue(value = "learnerId", defaultValue = "00") int learnerId) {
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);

		Strand strand = solidarityService.findStrandUsingStrandId(strandId);

		List<Score> scores = returnScores(strandId, learner);

		modelMap.addAttribute("scores", scores);
		modelMap.addAttribute("strandName", strand.getStrandName());

		return "subjectTeacherScorePage";
	}

	// sending the strand page to the class teacher with the strands
	@RequestMapping("/showStrandClassTeacher")
	public String showStrandsClassTeacher(@RequestParam("learningAreaId") int learningAreaId, ModelMap modelMap,HttpServletResponse response) {
		List<Strand> strands = returnAllStrands(learningAreaId);
		

		Cookie cookie = new Cookie("learningAreaId",String.valueOf(learningAreaId));
		response.addCookie(cookie);

		LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

		if (strands.isEmpty() == true) {

			return "redirect:/viewLearnerProfileDuplicate?confirm=true";
		}

		modelMap.addAttribute("strands", strands);
		modelMap.addAttribute("learningAreaName", learningArea.getLearningAreaName());

		return "classTeacherStrandPage";
	}
	
	
	// sending the strand page to the class teacher with the strands duplicate
		@RequestMapping("/showStrandClassTeacherDuplicate")
		public String showStrandsClassTeacherDuplicate(@CookieValue(value="learningAreaId",defaultValue="00") int learningAreaId, ModelMap modelMap,HttpServletResponse response) {
			List<Strand> strands = returnAllStrands(learningAreaId);
			

			Cookie cookie = new Cookie("learningAreaId",String.valueOf(learningAreaId));
			response.addCookie(cookie);

			LearningArea learningArea = solidarityService.findLearningAreaUsingId(learningAreaId);

			if (strands.isEmpty() == true) {

				return "redirect:/viewLearnerProfileDuplicate?confirm=true";
			}

			modelMap.addAttribute("strands", strands);
			modelMap.addAttribute("learningAreaName", learningArea.getLearningAreaName());

			return "classTeacherStrandPage";
		}

	// sending the scores to the subject teacher per substrand
	@RequestMapping("/showScoresClassTeacher")
	public String showScoresClassTeacher(@RequestParam("strandId") int strandId, ModelMap modelMap,
			@CookieValue(value = "learnerId", defaultValue = "00") int learnerId) {
		Learner learner = solidarityService.findLearnerUsingLearnerId(learnerId);

		Strand strand = solidarityService.findStrandUsingStrandId(strandId);

		List<Score> scores = returnScores(strandId, learner);

		modelMap.addAttribute("scores", scores);
		modelMap.addAttribute("strandName", strand.getStrandName());

		return "classTeacherScorePage";
	}
	
	
	//sending the change subject teacher name page to the browser
	@RequestMapping("/changeSubjectTeacherNamePage")
	public String changeSubjectTeacherNamePage(@RequestParam("phoneNumber")String phoneNumber,HttpServletResponse response)
	{
		Cookie cookie = new Cookie("subjectTeacherPhoneNumber",phoneNumber);
		response.addCookie(cookie);
		return"changeSubjectTeacherNamePage";
	}
	
	//changing the subject teacher phone number action
	@RequestMapping("/changeSubjectTeacherNameAction")
	public String changeSubjectTeacherNameAction(@CookieValue(value="subjectTeacherPhoneNumber",defaultValue="00")String phoneNumber,ModelMap modelMap,@RequestParam("firstName")String firstName,@RequestParam("secondName")String secondName,@RequestParam("lastName")String lastName)
	{
		SubjectTeacher subjectTeacher = solidarityService.findSubjectTeacherUsingPhoneNumber(phoneNumber);
		subjectTeacher.setFirst_name(firstName);
		subjectTeacher.setSecond_name(secondName);
		subjectTeacher.setLast_name(lastName);
		
		solidarityService.saveSubjectTeacher(subjectTeacher);
		modelMap.addAttribute("msg", "Username(s) changed successfully!");
	
	  return"changeSubjectTeacherNamePage";
	}
	
	
	
	//sending the change class teacher name page to the browser
		@RequestMapping("/changeClassTeacherNamePage")
		public String changeClassTeacherNamePage(@RequestParam("phoneNumber")String phoneNumber,HttpServletResponse response)
		{
			Cookie cookie = new Cookie("classTeacherPhoneNumber",phoneNumber);
			response.addCookie(cookie);
			return"changeClassTeacherNamePage";
		}
		
		//changing the subject teacher phone number action
		@RequestMapping("/changeClassTeacherNameAction")
		public String changeClassTeacherNameAction(@CookieValue(value="classTeacherPhoneNumber",defaultValue="00")String phoneNumber,ModelMap modelMap,@RequestParam("firstName")String firstName,@RequestParam("secondName")String secondName,@RequestParam("lastName")String lastName)
		{
			ClassTeacher classTeacher = solidarityService.findClassTeacherUsingPhoneNumber(phoneNumber);
			classTeacher.setFirst_name(firstName);
			classTeacher.setSecond_name(secondName);
			classTeacher.setLast_name(lastName);
			
			solidarityService.saveClassTeacher(classTeacher);
			modelMap.addAttribute("msg", "Username(s) changed successfully!");
		
		  return"changeClassTeacherNamePage";
		}
		
		
		//sending the change head teacher name page to the browser
				@RequestMapping("/changeHeadTeacherNamePage")
				public String changeHeadTeacherNamePage(@RequestParam("phoneNumber")String phoneNumber,HttpServletResponse response)
				{
					Cookie cookie = new Cookie("headTeacherPhoneNumber",phoneNumber);
					response.addCookie(cookie);
					return"changeHeadTeacherNamePage";
				}
				
				//changing the head teacher phone number action
				@RequestMapping("/changeHeadTeacherNameAction")
				public String changeHeadTeacherNameAction(@CookieValue(value="headTeacherPhoneNumber",defaultValue="00")String phoneNumber,ModelMap modelMap,@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName)
				{
					HeadTeacher headTeacher = solidarityService.findHeadTeacherUsingPhoneNumber(phoneNumber);
					headTeacher.setFirst_name(firstName);
					
					headTeacher.setLast_name(lastName);
					
					solidarityService.saveHeadTeacher(headTeacher);
					modelMap.addAttribute("msg", "Username(s) changed successfully!");
				
				  return"changeHeadTeacherNamePage";
				}
				
				
    //controller method for headteacher and classteacher profile redirect
	@RequestMapping("/redirectHeadClassTeacher")
	public String headTeacherClassTeacherRedirect(@CookieValue(value = "classTeacherPhoneNumber", defaultValue = "00") String classTeacherPhoneNumber,@CookieValue(value = "headTeacherPhoneNumber", defaultValue = "00") String headTeacherPhoneNumber)
	{
		
		System.out.println("head:" +headTeacherPhoneNumber);
		System.out.println("class:" +classTeacherPhoneNumber);
		
		if(classTeacherPhoneNumber.equals("00"))
		{
			return"redirect:/verifyHeadTeacherDuplicate";
		}
		else if(headTeacherPhoneNumber.equals("00"))
		{
			return"redirect:/verifyClassTeacherDuplicate";
		}
		
		else
		{
			return"redirect:/logout";
		}
		
		
	}
	
	
	//Returning enroller status page to browser
	@RequestMapping("/enrollerProfiles")
	public String enrollerProfiles(ModelMap modelMap)
	{
		List<Enroller> enrollers = solidarityService.findAllEnrollers();
		
		 modelMap.addAttribute("enrollers", enrollers);
		
		return"enrollerStatusPage";
	}
	
	
	//returning the enroller number of schools
	@RequestMapping("/enrollerSchools")
	public String enrollerSchools(@RequestParam("enrollerPhoneNumber")String enrollerPhoneNumber,ModelMap modelMap)
	{
		
		Enroller enroller = solidarityService.findEnrollerUsingPhoneNumber(enrollerPhoneNumber);
		
		List<School> schools = enroller.getSchools();
		if(schools.isEmpty()==true)
		{
			int schoolNumber = 0;
			
			modelMap.addAttribute("schoolNumber", schoolNumber);
		}
		else
		{
			int schoolNumber = schools.size();
			modelMap.addAttribute("schoolNumber", schoolNumber);
		}
		
		
		return"enrollerSchoolNumberPage";
	}
	
	
	
	
	
	
}
