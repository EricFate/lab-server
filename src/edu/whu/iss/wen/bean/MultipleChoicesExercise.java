package edu.whu.iss.wen.bean;

import java.util.Set;

public class MultipleChoicesExercise {
	int id;
	String question;
	String option1;
	String option2;
	String option3;
	String option4;
	String analysis;
	String imageURL;   //有些题目有图
	int answer;
	int finishedTimes;
	int correctTimes;
	Set<Knowledge> knowledges;
	ExerciseSubject subject;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getFinishedTimes() {
		return finishedTimes;
	}
	public void setFinishedTimes(int finishedTimes) {
		this.finishedTimes = finishedTimes;
	}
	public int getCorrectTimes() {
		return correctTimes;
	}
	public void setCorrectTimes(int correctTimes) {
		this.correctTimes = correctTimes;
	}
	public Set<Knowledge> getKnowledges() {
		return knowledges;
	}
	public void setKnowledges(Set<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}
	public ExerciseSubject getSubject() {
		return subject;
	}
	public void setSubject(ExerciseSubject subject) {
		this.subject = subject;
	}
	
}
