package edu.whu.iss.sd.bean;

import java.util.Date;

import com.google.gson.annotations.JsonAdapter;

public class Answer {
	private int id;
	private String content;
	private Date time;
	private boolean anonymous;
	private int agree;
	private String uno;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [content=" + content + "]";
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
	
	private CollegeStudent collegeStudent;
	private Question question;
	
	
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
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * @return the anonymous
	 */
	public boolean isAnonymous() {
		return anonymous;
	}
	/**
	 * @param anonymous the anonymous to set
	 */
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	/**
	 * @return the agree
	 */
	public int getAgree() {
		return agree;
	}
	/**
	 * @param agree the agree to set
	 */
	public void setAgree(int agree) {
		this.agree = agree;
	}
	/**
	 * @return the uno
	 */
	public String getUno() {
		return uno;
	}
	/**
	 * @param uno the uno to set
	 */
	public void setUno(String uno) {
		this.uno = uno;
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
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
