package edu.whu.iss.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fate on 2016/12/8.
 */

public class Issue {

	public Issue(int id, String title, Student user) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
	}

	private int id;
	private Date time;
	private String title;
	private String content;
	private Student user;
	private boolean anonymous;
	private int answerNumber;
	private Set<Answer> answers = new HashSet<Answer>();

	
	public Issue(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Issue(int id, String title, String content, Student user,
			boolean anonymous, int answerNumber) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.user = user;
		this.anonymous = anonymous;
		this.answerNumber = answerNumber;
	}

	public Issue() {
		super();
	}





	public int getAnswerNumber() {
		return answerNumber;
	}
	
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}




	public Date getTime() {
		return time;
	}





	public void setTime(Date time) {
		this.time = time;
	}





	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Student getUser() {
		return user;
	}

	public void setUser(Student user) {
		this.user = user;
	}

}
