package edu.whu.iss.wen.bean;

import java.util.Set;

import edu.whu.iss.bean.PrivateExDetail;

public class Exercise {
	int id;
	int answer;
	int tendency;
	int type;
	String title;
	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String analysis;
	int right;
	int number;
	int accuracy;
	double difficulty;
    Set<ExerciseCatagory> exerciseCatagory;
    Set<PrivateExDetail> exDetails;
    
    public Exercise() {
		super();
	}
    
	public Exercise(int id) {
		super();
		this.id=id;
	}
	public Set<PrivateExDetail> getExDetails() {
		return exDetails;
	}
	public void setExDetails(Set<PrivateExDetail> exDetails) {
		this.exDetails = exDetails;
	}
	public Set<ExerciseCatagory> getExerciseCatagory() {
		return exerciseCatagory;
	}
	public void setExerciseCatagory(Set<ExerciseCatagory> exerciseCatagory) {
		this.exerciseCatagory = exerciseCatagory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getTendency() {
		return tendency;
	}
	public void setTendency(int tendency) {
		this.tendency = tendency;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	
	
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}


	
}
