package com.solidarity.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solidarity.model.Grade;
import com.solidarity.model.Learner;
import com.solidarity.model.LearningArea;
import com.solidarity.model.ResultRecord;
import com.solidarity.model.Score;
import com.solidarity.model.Strand;
import com.solidarity.model.SubStrand;
import com.solidarity.service.SolidarityService;

@Component
public class CalculateResultsImpl implements CalculateResults {

	@Autowired
	SolidarityService solidarityService;

	// generate the overal grade mean Score
	public int generateOveralGradeMinScore(Grade grade, int term) {

		switch (term) {

		case 1:

			if (grade.isBeenTermOne() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> overalPastScores = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learner = learnersInGrade.get(i);

					List<ResultRecord> resultsRecords = learner.getResultRecords();

					if (resultsRecords.isEmpty() == true) {
						List<LearningArea> learningAreas = learner.getLearning_areas();
						List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

						for (int a = 0; a < learningAreas.size(); a++) {

							LearningArea learningArea = learningAreas.get(a);
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
											System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
													+ scores.isEmpty());

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
														if (score.getLearner().getLearnerId() == learner
																.getLearnerId()) {
															scoreValues.add(score.getScore_value());
															System.out
																	.println("This is it now" + score.getScore_value());
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
										if(scoreValues.size()!=0)
										{
											
											
											overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

											strandValues.add(overalScorePerStrand);
										}
										
										else
										{
											overalScorePerStrand =0;
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

								evaluatedLearningAreas.add(learningArea);

							}
						}

						List<Integer> learningAreaScores = new ArrayList<Integer>();

						for (int b = 0; b < evaluatedLearningAreas.size(); b++) {
							LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(b);

							learningAreaScores.add(evalueatedLearningArea.getAverageScore());

							System.out.println(evalueatedLearningArea.getLearningAreaName()
									+ evalueatedLearningArea.getAverageScore());
						}

						int totalPoint = 0;

						for (int c = 0; c < learningAreaScores.size(); c++) {
							totalPoint += learningAreaScores.get(c);
						}

						if (learningAreaScores.size() == 0) {
							int overalPoint = 0;

							overalPastScores.add(overalPoint);

						} else {
							int overalPoint = Math.round(totalPoint / learningAreaScores.size());
							overalPastScores.add(overalPoint);
						}

					}

					else {

						List<Integer> pastScoresPerLearner = new ArrayList<Integer>();

						for (int k = 0; k < resultsRecords.size(); k++) {
							ResultRecord resultRecord = resultsRecords.get(k);
							pastScoresPerLearner.add(resultRecord.getOveralTermOneScore());

						}

						int sumOfLearningAreaPastScores = 0;

						for (int x = 0; x < pastScoresPerLearner.size(); x++) {
							sumOfLearningAreaPastScores = sumOfLearningAreaPastScores + pastScoresPerLearner.get(x);
						}

						int averagePastScore = Math.round(sumOfLearningAreaPastScores / resultsRecords.size());

						overalPastScores.add(averagePastScore);

					}

				}

				int sumOfGradePastScores = 0;

				for (int y = 0; y < overalPastScores.size(); y++) {
					sumOfGradePastScores = sumOfGradePastScores + overalPastScores.get(y);
				}

				int averageGradePastScores = Math.round(sumOfGradePastScores / learnersInGrade.size());

				return averageGradePastScores;

			}

			else {
				return 0;
			}

		case 2:

			if (grade.isBeenTermTwo() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> overalPastScores = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learner = learnersInGrade.get(i);

					List<ResultRecord> resultsRecords = learner.getResultRecords();

					if (resultsRecords.isEmpty() == true) {
						List<LearningArea> learningAreas = learner.getLearning_areas();
						List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

						for (int a = 0; a < learningAreas.size(); a++) {

							LearningArea learningArea = learningAreas.get(a);
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
											System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
													+ scores.isEmpty());

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
														if (score.getLearner().getLearnerId() == learner
																.getLearnerId()) {
															scoreValues.add(score.getScore_value());
															System.out
																	.println("This is it now" + score.getScore_value());
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
										if(scoreValues.size()!=0)
										{
											
											
											overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

											strandValues.add(overalScorePerStrand);
										}
										
										else
										{
											overalScorePerStrand =0;
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

								evaluatedLearningAreas.add(learningArea);

							}
						}

						List<Integer> learningAreaScores = new ArrayList<Integer>();

						for (int b = 0; b < evaluatedLearningAreas.size(); b++) {
							LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(b);

							learningAreaScores.add(evalueatedLearningArea.getAverageScore());

							System.out.println(evalueatedLearningArea.getLearningAreaName()
									+ evalueatedLearningArea.getAverageScore());
						}

						int totalPoint = 0;

						for (int c = 0; c < learningAreaScores.size(); c++) {
							totalPoint += learningAreaScores.get(c);
						}

						if (learningAreaScores.size() == 0) {
							int overalPoint = 0;

							overalPastScores.add(overalPoint);

						} else {
							int overalPoint = Math.round(totalPoint / learningAreaScores.size());
							overalPastScores.add(overalPoint);
						}

					}

					else {

						List<Integer> pastScoresPerLearner = new ArrayList<Integer>();

						for (int k = 0; k < resultsRecords.size(); k++) {
							ResultRecord resultRecord = resultsRecords.get(k);
							pastScoresPerLearner.add(resultRecord.getOveralTermTwoScore());

						}

						int sumOfLearningAreaPastScores = 0;

						for (int x = 0; x < pastScoresPerLearner.size(); x++) {
							sumOfLearningAreaPastScores = sumOfLearningAreaPastScores + pastScoresPerLearner.get(x);
						}

						int averagePastScore = Math.round(sumOfLearningAreaPastScores / resultsRecords.size());

						overalPastScores.add(averagePastScore);

					}

				}

				int sumOfGradePastScores = 0;

				for (int y = 0; y < overalPastScores.size(); y++) {
					sumOfGradePastScores = sumOfGradePastScores + overalPastScores.get(y);
				}

				int averageGradePastScores = Math.round(sumOfGradePastScores / learnersInGrade.size());

				return averageGradePastScores;

			}

			else if (grade.isBeenTermTwo() == false) {
				return 0;
			}
			break;

		case 3:

			if (grade.isBeenTermThree() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> overalPastScores = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learner = learnersInGrade.get(i);

					List<ResultRecord> resultsRecords = learner.getResultRecords();

					if (resultsRecords.isEmpty() == true) {
						List<LearningArea> learningAreas = learner.getLearning_areas();
						List<LearningArea> evaluatedLearningAreas = new ArrayList<LearningArea>();

						for (int a = 0; a < learningAreas.size(); a++) {

							LearningArea learningArea = learningAreas.get(a);
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
											System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
													+ scores.isEmpty());

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
														if (score.getLearner().getLearnerId() == learner
																.getLearnerId()) {
															scoreValues.add(score.getScore_value());
															System.out
																	.println("This is it now" + score.getScore_value());
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
										if(scoreValues.size()!=0)
										{
											
											
											overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

											strandValues.add(overalScorePerStrand);
										}
										
										else
										{
											overalScorePerStrand =0;
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

								evaluatedLearningAreas.add(learningArea);

							}
						}

						List<Integer> learningAreaScores = new ArrayList<Integer>();

						for (int b = 0; b < evaluatedLearningAreas.size(); b++) {
							LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(b);

							learningAreaScores.add(evalueatedLearningArea.getAverageScore());

							System.out.println(evalueatedLearningArea.getLearningAreaName()
									+ evalueatedLearningArea.getAverageScore());
						}

						int totalPoint = 0;

						for (int c = 0; c < learningAreaScores.size(); c++) {
							totalPoint += learningAreaScores.get(c);
						}

						if (learningAreaScores.size() == 0) {
							int overalPoint = 0;

							overalPastScores.add(overalPoint);

						} else {
							int overalPoint = Math.round(totalPoint / learningAreaScores.size());
							overalPastScores.add(overalPoint);
						}

					}

					else {

						List<Integer> pastScoresPerLearner = new ArrayList<Integer>();

						for (int k = 0; k < resultsRecords.size(); k++) {
							ResultRecord resultRecord = resultsRecords.get(k);
							pastScoresPerLearner.add(resultRecord.getOveralTermThreeScore());

						}

						int sumOfLearningAreaPastScores = 0;

						for (int x = 0; x < pastScoresPerLearner.size(); x++) {
							sumOfLearningAreaPastScores = sumOfLearningAreaPastScores + pastScoresPerLearner.get(x);
						}

						int averagePastScore = Math.round(sumOfLearningAreaPastScores / resultsRecords.size());

						overalPastScores.add(averagePastScore);

					}

				}

				int sumOfGradePastScores = 0;

				for (int y = 0; y < overalPastScores.size(); y++) {
					sumOfGradePastScores = sumOfGradePastScores + overalPastScores.get(y);
				}

				int averageGradePastScores = Math.round(sumOfGradePastScores / learnersInGrade.size());

				return averageGradePastScores;

			}

			else {
				return 0;
			}

		}

		return 0;
	}

	// generate overall learner's score all learning areas combined
	@Override
	public int generateLearnerOveralScore(Learner learner, int term) {

		Grade grade = learner.getGrade();
		switch (term) {
		case 1:

			if (grade.isBeenTermOne() == true) {
				List<ResultRecord> resultRecords = learner.getResultRecords();

				if (resultRecords.isEmpty() == true) {
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
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

							evaluatedLearningAreas.add(learningArea);

						}
					}

					List<Integer> learningAreaScores = new ArrayList<Integer>();

					for (int i = 0; i < evaluatedLearningAreas.size(); i++) {
						LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(i);

						learningAreaScores.add(evalueatedLearningArea.getAverageScore());

						System.out.println(evalueatedLearningArea.getLearningAreaName()
								+ evalueatedLearningArea.getAverageScore());
					}

					int totalPoint = 0;

					for (int i = 0; i < learningAreaScores.size(); i++) {
						totalPoint += learningAreaScores.get(i);
					}

					if (learningAreaScores.size() == 0) {
						int overalPoint = 0;

						return overalPoint;

					} else {
						int overalPoint = Math.round(totalPoint / learningAreaScores.size());
						return overalPoint;
					}

				}

				else if (resultRecords.isEmpty() == false) {
					List<Integer> resultRecordValues = new ArrayList<Integer>();

					for (int i = 0; i < resultRecords.size(); i++) {
						ResultRecord resultRecord = resultRecords.get(i);
						resultRecordValues.add(resultRecord.getOveralTermOneScore());

					}

					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int overalScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return overalScore;
				}

			}

			else if (grade.isBeenTermOne() == false) {

				return 0;

			}

			break;

		case 2:

			if (grade.isBeenTermTwo() == true) {
				List<ResultRecord> resultRecords = learner.getResultRecords();

				if (resultRecords.isEmpty() == true) {
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
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

							evaluatedLearningAreas.add(learningArea);

						}
					}

					List<Integer> learningAreaScores = new ArrayList<Integer>();

					for (int i = 0; i < evaluatedLearningAreas.size(); i++) {
						LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(i);

						learningAreaScores.add(evalueatedLearningArea.getAverageScore());

						System.out.println(evalueatedLearningArea.getLearningAreaName()
								+ evalueatedLearningArea.getAverageScore());
					}

					int totalPoint = 0;

					for (int i = 0; i < learningAreaScores.size(); i++) {
						totalPoint += learningAreaScores.get(i);
					}

					if (learningAreaScores.size() == 0) {
						int overalPoint = 0;

						return overalPoint;

					} else {
						int overalPoint = Math.round(totalPoint / learningAreaScores.size());
						return overalPoint;
					}

				}

				else if (resultRecords.isEmpty() == false) {
					List<Integer> resultRecordValues = new ArrayList<Integer>();

					for (int i = 0; i < resultRecords.size(); i++) {
						ResultRecord resultRecord = resultRecords.get(i);
						resultRecordValues.add(resultRecord.getOveralTermTwoScore());

					}

					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int overalScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return overalScore;
				}

			}

			else if (grade.isBeenTermTwo() == false) {

				return 0;

			}

			break;

		case 3:

			if (grade.isBeenTermThree() == true) {
				List<ResultRecord> resultRecords = learner.getResultRecords();

				if (resultRecords.isEmpty() == true) {
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
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

							evaluatedLearningAreas.add(learningArea);

						}
					}

					List<Integer> learningAreaScores = new ArrayList<Integer>();

					for (int i = 0; i < evaluatedLearningAreas.size(); i++) {
						LearningArea evalueatedLearningArea = evaluatedLearningAreas.get(i);

						learningAreaScores.add(evalueatedLearningArea.getAverageScore());

						System.out.println(evalueatedLearningArea.getLearningAreaName()
								+ evalueatedLearningArea.getAverageScore());
					}

					int totalPoint = 0;

					for (int i = 0; i < learningAreaScores.size(); i++) {
						totalPoint += learningAreaScores.get(i);
					}

					if (learningAreaScores.size() == 0) {
						int overalPoint = 0;

						return overalPoint;

					} else {
						int overalPoint = Math.round(totalPoint / learningAreaScores.size());
						return overalPoint;
					}

				}

				else if (resultRecords.isEmpty() == false) {
					List<Integer> resultRecordValues = new ArrayList<Integer>();

					for (int i = 0; i < resultRecords.size(); i++) {
						ResultRecord resultRecord = resultRecords.get(i);
						resultRecordValues.add(resultRecord.getOveralTermThreeScore());

					}

					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int overalScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return overalScore;
				}

			}

			else if (grade.isBeenTermThree() == false) {

				return 0;

			}

			break;

		}
		return 0;
	}

	// generate overall learner's performance per learning area
	@Override
	public int generateLearnerOveralScorePerLearningArea(Learner learner, LearningArea learningArea, int term,
			Grade grade) {

		switch (term) {
		case 1:
			if (grade.isBeenTermOne() == true) {
				ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(learner,
						learningArea.getLearningAreaName());

				if (resultRecord == null) {
					List<Strand> strands = learningArea.getStrands();

					if (strands.isEmpty() == true) {
						return 0;
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
								if(scoreValues.size()!=0)
								{
									
									
									overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

									strandValues.add(overalScorePerStrand);
								}
								
								else
								{
									overalScorePerStrand =0;
								}

								strandValues.add(overalScorePerStrand);

							}

						}

						for (int k = 0; k < strandValues.size(); k++) {
							totalStrandMarks += strandValues.get(k);

						}

						System.out.println("The total marks is " + totalStrandMarks);

						System.out.println("The strandValue size is" + strandValues.size());

						int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

						return overalScorePerLearningArea;

					}
				}

				else {
					return resultRecord.getOveralTermOneScore();
				}

			}
			break;

		case 2:
			if (grade.isBeenTermTwo() == true) {
				ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(learner,
						learningArea.getLearningAreaName());

				if (resultRecord == null) {
					List<Strand> strands = learningArea.getStrands();

					if (strands.isEmpty() == true) {
						return 0;
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
								if(scoreValues.size()!=0)
								{
									
									
									overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

									strandValues.add(overalScorePerStrand);
								}
								
								else
								{
									overalScorePerStrand =0;
								}

								strandValues.add(overalScorePerStrand);

							}

						}

						for (int k = 0; k < strandValues.size(); k++) {
							totalStrandMarks += strandValues.get(k);

						}

						System.out.println("The total marks is " + totalStrandMarks);

						System.out.println("The strandValue size is" + strandValues.size());

						int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

						return overalScorePerLearningArea;

					}
				}

				else {
					return resultRecord.getOveralTermTwoScore();
				}

			}
			break;

		case 3:
			if (grade.isBeenTermThree() == true) {
				ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(learner,
						learningArea.getLearningAreaName());

				if (resultRecord == null) {
					List<Strand> strands = learningArea.getStrands();

					if (strands.isEmpty() == true) {
						return 0;
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
								if(scoreValues.size()!=0)
								{
									
									
									overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

									strandValues.add(overalScorePerStrand);
								}
								
								else
								{
									overalScorePerStrand =0;
								}

								strandValues.add(overalScorePerStrand);

							}

						}

						for (int k = 0; k < strandValues.size(); k++) {
							totalStrandMarks += strandValues.get(k);

						}

						System.out.println("The total marks is " + totalStrandMarks);

						System.out.println("The strandValue size is" + strandValues.size());

						int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

						return overalScorePerLearningArea;

					}
				}

				else {
					return resultRecord.getOveralTermTwoScore();
				}

			}
			break;

		}

		return 0;
	}

	// generate the overall Grade mean score per learning area
	@Override
	public int generateOveralGradeMinPerLearningArea(Grade grade, LearningArea learningArea, int term) {

		switch (term) {
		case 1:
			if (grade.isBeenTermOne() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> resultRecordValues = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learnerInGrade = learnersInGrade.get(i);

					ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(
							learnerInGrade, learningArea.getLearningAreaName());

					resultRecordValues.add(resultRecord.getOveralTermOneScore());

				}

				if (resultRecordValues.isEmpty() == false) {
					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int averageGradeLearningAreaScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return averageGradeLearningAreaScore;
				} else {

					List<Integer> learningAreaValues = new ArrayList<Integer>();
					for (int y = 0; y < learnersInGrade.size(); y++) {

						Learner learner = learnersInGrade.get(y);

						List<Strand> strands = learningArea.getStrands();

						if (strands.isEmpty() == true) {
							learningAreaValues.add(0);
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
									}

									strandValues.add(overalScorePerStrand);

								}

							}

							for (int k = 0; k < strandValues.size(); k++) {
								totalStrandMarks += strandValues.get(k);

							}

							System.out.println("The total marks is " + totalStrandMarks);

							System.out.println("The strandValue size is" + strandValues.size());

							int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

							learningAreaValues.add(overalScorePerLearningArea);

						}

					}

					int sumOfLearningAreaValues = 0;

					for (int x = 0; x < learningAreaValues.size(); x++) {
						sumOfLearningAreaValues += learningAreaValues.get(x);
					}

					int averageSumOfLearningAreaValues = Math
							.round(sumOfLearningAreaValues / learningAreaValues.size());

					return averageSumOfLearningAreaValues;

				}
			}

			else if (grade.isBeenTermOne() == false) {
				return 0;
			}

			break;

		case 2:
			if (grade.isBeenTermOne() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> resultRecordValues = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learnerInGrade = learnersInGrade.get(i);

					ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(
							learnerInGrade, learningArea.getLearningAreaName());

					resultRecordValues.add(resultRecord.getOveralTermTwoScore());

				}

				if (resultRecordValues.isEmpty() == false) {
					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int averageGradeLearningAreaScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return averageGradeLearningAreaScore;
				} else {

					List<Integer> learningAreaValues = new ArrayList<Integer>();
					for (int y = 0; y < learnersInGrade.size(); y++) {

						Learner learner = learnersInGrade.get(y);

						List<Strand> strands = learningArea.getStrands();

						if (strands.isEmpty() == true) {
							learningAreaValues.add(0);
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
									}
									strandValues.add(overalScorePerStrand);

								}

							}

							for (int k = 0; k < strandValues.size(); k++) {
								totalStrandMarks += strandValues.get(k);

							}

							System.out.println("The total marks is " + totalStrandMarks);

							System.out.println("The strandValue size is" + strandValues.size());

							int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

							learningAreaValues.add(overalScorePerLearningArea);

						}

					}

					int sumOfLearningAreaValues = 0;

					for (int x = 0; x < learningAreaValues.size(); x++) {
						sumOfLearningAreaValues += learningAreaValues.get(x);
					}

					int averageSumOfLearningAreaValues = Math
							.round(sumOfLearningAreaValues / learningAreaValues.size());

					return averageSumOfLearningAreaValues;

				}
			}

			else if (grade.isBeenTermTwo() == false) {
				return 0;
			}
			break;

		case 3:
			if (grade.isBeenTermThree() == true) {
				List<Learner> learnersInGrade = grade.getLearners();
				List<Integer> resultRecordValues = new ArrayList<Integer>();

				for (int i = 0; i < learnersInGrade.size(); i++) {
					Learner learnerInGrade = learnersInGrade.get(i);

					ResultRecord resultRecord = solidarityService.findResultRecordUsingLearnerIdAndLearningAreaName(
							learnerInGrade, learningArea.getLearningAreaName());

					resultRecordValues.add(resultRecord.getOveralTermThreeScore());

				}

				if (resultRecordValues.isEmpty() == false) {
					int sumOfResultRecordValues = 0;

					for (int x = 0; x < resultRecordValues.size(); x++) {
						sumOfResultRecordValues += resultRecordValues.get(x);
					}

					int averageGradeLearningAreaScore = Math.round(sumOfResultRecordValues / resultRecordValues.size());

					return averageGradeLearningAreaScore;
				} else {

					List<Integer> learningAreaValues = new ArrayList<Integer>();
					for (int y = 0; y < learnersInGrade.size(); y++) {

						Learner learner = learnersInGrade.get(y);

						List<Strand> strands = learningArea.getStrands();

						if (strands.isEmpty() == true) {
							learningAreaValues.add(0);
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
										System.out.println(subStrand.getSubStrandName() + " The scores is empty ?"
												+ scores.isEmpty());

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
									if(scoreValues.size()!=0)
									{
										
										
										overalScorePerStrand = Math.round(totalSubStrandMarks / scoreValues.size());

										strandValues.add(overalScorePerStrand);
									}
									
									else
									{
										overalScorePerStrand =0;
									}
									strandValues.add(overalScorePerStrand);

								}

							}

							for (int k = 0; k < strandValues.size(); k++) {
								totalStrandMarks += strandValues.get(k);

							}

							System.out.println("The total marks is " + totalStrandMarks);

							System.out.println("The strandValue size is" + strandValues.size());

							int overalScorePerLearningArea = Math.round(totalStrandMarks / strandValues.size());

							learningAreaValues.add(overalScorePerLearningArea);

						}

					}

					int sumOfLearningAreaValues = 0;

					for (int x = 0; x < learningAreaValues.size(); x++) {
						sumOfLearningAreaValues += learningAreaValues.get(x);
					}

					int averageSumOfLearningAreaValues = Math
							.round(sumOfLearningAreaValues / learningAreaValues.size());

					return averageSumOfLearningAreaValues;

				}
			}

			else if (grade.isBeenTermThree() == false) {
				return 0;
			}
			break;

		}

		return 0;
	}

}
