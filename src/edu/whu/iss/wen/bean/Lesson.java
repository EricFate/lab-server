package edu.whu.iss.wen.bean;

import java.util.Set;

public class Lesson {
    int id;
    String lessonName;
    String knowledgePoint;
    String description;
    String videoURL;
    Chapter chapter;
    String videoImageURL;

    ExerciseCatagory exerciseCatagory;
	public ExerciseCatagory getExerciseCatagory() {
		return exerciseCatagory;
	}
	public void setExerciseCatagory(ExerciseCatagory exerciseCatagory) {
		this.exerciseCatagory = exerciseCatagory;
	}
	public String getVideoImageURL() {
		return videoImageURL;
	}
	public void setVideoImageURL(String videoImageURL) {
		this.videoImageURL = videoImageURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
    
    
}
