package edu.whu.iss.bean;

public class PrivateExCate {
	private int id;
	private String name;
	private int total;
	private int progress;
	private int correct;
	private int totalAnswer;
	private int type;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getTotalAnswer() {
		return totalAnswer;
	}
	public void setTotalAnswer(int totalAnswer) {
		this.totalAnswer = totalAnswer;
	}
}
