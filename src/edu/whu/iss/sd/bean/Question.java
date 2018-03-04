package edu.whu.iss.sd.bean;

import java.util.HashSet;
import java.util.Set;

public class Question {
	private int id;
	private String title;
	private String date;
	private String content;
	private String foucusnumber;
	private String category;
	private Set<Answer> answers = new HashSet<Answer>();
	private CollegeStudent collegeStudent;
	private String questionerName;
	private String questionerImage;
	
	/**
	 * @return the questionerImage
	 */
	public String getQuestionerImage() {
		return questionerImage;
	}
	/**
	 * @param questionerImage the questionerImage to set
	 */
	public void setQuestionerImage(String questionerImage) {
		this.questionerImage = questionerImage;
	}
	/**
	 * @return the questionerName
	 */
	public String getQuestionerName() {
		return questionerName;
	}
	/**
	 * @param questionerName the questionerName to set
	 */
	public void setQuestionerName(String questionerName) {
		this.questionerName = questionerName;
	}
	
	/**
	 * @return the collegeStudent
	 */
	public CollegeStudent getCollegeStudent() {
		return collegeStudent;
	}
	/**
	 * @param collegeStudent the collegeStudent to set
	 */
	public void setCollegeStudent(CollegeStudent collegeStudent) {
		this.collegeStudent = collegeStudent;
	}
	/**
	 * @return the answers
	 */
	public Set<Answer> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the foucusnumber
	 */
	public String getFoucusnumber() {
		return foucusnumber;
	}
	/**
	 * @param foucusnumber the foucusnumber to set
	 */
	public void setFoucusnumber(String foucusnumber) {
		this.foucusnumber = foucusnumber;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + "]";
	}
	

}
