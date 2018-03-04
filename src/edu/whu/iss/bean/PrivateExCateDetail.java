package edu.whu.iss.bean;

import edu.whu.iss.wen.bean.ExerciseCatagory;

public class PrivateExCateDetail {
	private int id;
	private int correct;
	private int progress;
	private int total;
	private Student  student;
	private ExerciseCatagory exerciseCatagory;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public ExerciseCatagory getExerciseCatagory() {
		return exerciseCatagory;
	}
	public void setExerciseCatagory(ExerciseCatagory exerciseCatagory) {
		this.exerciseCatagory = exerciseCatagory;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
}
