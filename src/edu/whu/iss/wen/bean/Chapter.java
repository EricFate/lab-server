package edu.whu.iss.wen.bean;

import java.util.HashSet;
import java.util.Set;

public class Chapter {
	int id;

	int isSigned;
	String chapterName;
	int lessonNumber;
	String knowledgePoint;
	String description;
	Course course;
    Set<Lesson> lessons=new HashSet<Lesson>();
    ExerciseCatagory exerciseCatagory;
    
	public ExerciseCatagory getExerciseCatagory() {
		return exerciseCatagory;
	}
	public void setExerciseCatagory(ExerciseCatagory exerciseCatagory) {
		this.exerciseCatagory = exerciseCatagory;
	}

	public Set<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(int isSigned) {
		this.isSigned = isSigned;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}




	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public String getKnowledgePoint() {
		return knowledgePoint;
	}

	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
