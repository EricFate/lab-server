package edu.whu.iss.bean;

import java.util.Date;

/**
 * Created by fate on 2016/12/9.
 */

public class Answer {

	private int id;
	private String content;
	private Date time;
	private boolean anonymous;
	private Issue issue;
	private int agree;
	private String uno;
	private Info answerer;
	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public Info getAnswerer() {
		return answerer;
	}

	public void setAnswerer(Info answerer) {
		this.answerer = answerer;
	}

}
