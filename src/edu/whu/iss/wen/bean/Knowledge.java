package edu.whu.iss.wen.bean;

import java.util.Set;

import edu.whu.iss.bean.Student;

public class Knowledge {
	int id;
	String label;
	String description;
	ExerciseSubject subject;
	String videoURL;
	Set<FillInBlankExercise> fillInBlankExercises;
	Set<MultipleChoicesExercise> multipleChoicesExercises;
	Set<KnowledgeRelationship> relationships1;
	Set<KnowledgeRelationship> relationships2;
	Set<Student> weakStudents;  //记录没有掌握该知识点的同学
	float totalAccuracy; //该知识点下所有题目的总正确率
	
	public Set<Student> getWeakStudents() {
		return weakStudents;
	}
	public void setWeakStudents(Set<Student> weakStudents) {
		this.weakStudents = weakStudents;
	}
	
	public Set<KnowledgeRelationship> getRelationships1() {
		return relationships1;
	}
	public void setRelationships1(Set<KnowledgeRelationship> relationships1) {
		this.relationships1 = relationships1;
	}
	public Set<KnowledgeRelationship> getRelationships2() {
		return relationships2;
	}
	public void setRelationships2(Set<KnowledgeRelationship> relationships2) {
		this.relationships2 = relationships2;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public float getTotalAccuracy() {
		return totalAccuracy;
	}
	public void setTotalAccuracy(float totalAccuracy) {
		this.totalAccuracy = totalAccuracy;
	}
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
	public Knowledge(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ExerciseSubject getSubject() {
		return subject;
	}
	public void setSubject(ExerciseSubject subject) {
		this.subject = subject;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	
}
