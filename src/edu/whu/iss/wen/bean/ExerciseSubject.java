package edu.whu.iss.wen.bean;

import java.util.Set;

public class ExerciseSubject {
	int id;
	String subject;
	int grade;
	Set<Knowledge> knowledges;
	Set<FillInBlankExercise> fillInBlankExercises;
	Set<MultipleChoicesExercise> multipleChoicesExercises;
	
	public Set<FillInBlankExercise> getFillInBlankExercises() {
		return fillInBlankExercises;
	}
	public void setFillInBlankExercises(Set<FillInBlankExercise> fillInBlankExercises) {
		this.fillInBlankExercises = fillInBlankExercises;
	}
	public Set<MultipleChoicesExercise> getMultipleChoicesExercises() {
		return multipleChoicesExercises;
	}
	public void setMultipleChoicesExercises(Set<MultipleChoicesExercise> multipleChoicesExercises) {
		this.multipleChoicesExercises = multipleChoicesExercises;
	}
	public ExerciseSubject(){}
	public ExerciseSubject(int id, String subject, int grade){
		this.id = id;
		this.subject = subject;
		this.grade = grade;
	}
	public ExerciseSubject(String subject, int grade){
		this.subject = subject;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Set<Knowledge> getKnowledges() {
		return knowledges;
	}
	public void setKnowledges(Set<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}
}
