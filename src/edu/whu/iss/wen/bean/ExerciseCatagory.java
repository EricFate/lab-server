package edu.whu.iss.wen.bean;

import java.util.Set;

import edu.whu.iss.bean.PrivateExCateDetail;

public class ExerciseCatagory {
	int id;
	int type;
	String name;
	double accuracy;
	int total;
	Course course;
	Chapter chapter;
	Lesson lesson;
	Set<Exercise> exercises;
	Set<PrivateExCateDetail> exCateDetails;
	
	
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Set<PrivateExCateDetail> getExCateDetails() {
		return exCateDetails;
	}
	public void setExCateDetails(Set<PrivateExCateDetail> exCateDetails) {
		this.exCateDetails = exCateDetails;
	}
	public Set<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "ExerciseCatagory [id=" + id + ", type=" + type + ", name=" + name + ", accuracy=" + accuracy
				+ ", total=" + total + ", course=" + course + ", lesson=" + lesson + ", exercises=" + exercises
				+ ", exCateDetails=" + exCateDetails + "]";
	}
	

}
