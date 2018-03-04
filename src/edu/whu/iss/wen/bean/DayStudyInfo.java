package edu.whu.iss.wen.bean;

import java.util.Date;
import java.util.List;
import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.MessageRecord;

public class DayStudyInfo {
	
	Date time;
	List<CourseLearning> learnings;
	List<MessageRecord> messages;
	List<Issue> issues;
	List<Answer> answers;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<CourseLearning> getLearning() {
		return learnings;
	}
	public void setLearning(List<CourseLearning> learning) {
		this.learnings = learning;
	}
	public List<MessageRecord> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageRecord> messages) {
		this.messages = messages;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	
	
}
