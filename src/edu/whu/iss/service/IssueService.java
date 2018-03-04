package edu.whu.iss.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.Student;
import edu.whu.iss.dao.IssuesDAO;
import edu.whu.iss.utils.HibernateUtils;

public class IssueService {
	private static IssueService instance = new IssueService();
	
	public static IssueService getInstance() {
		return instance;
	}


	private IssuesDAO issuesDAO = new IssuesDAO();
	public void saveAnswer(Answer answer,String uid,int iid){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Issue issue = session.get(Issue.class, iid);
		answer.setIssue(issue);
		answer.setUno(uid);
		issue.setAnswerNumber(issue.getAnswerNumber()+1);
		issuesDAO.saveAnswer(answer);
		transaction.commit();
	}
	public Set<Answer> getAnswers(int iid){
		 return issuesDAO.getAnswers(iid);
	}
	public List<Issue> getIssues(int start){
		return issuesDAO.getIssues(start);
	}
	public void saveIssue(Issue issue,int id){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		issue.setUser(student);
		issuesDAO.saveIssue(issue);
		transaction.commit();
	}


	public void agreeAnswer(int aid) {
		// TODO Auto-generated method stub
		issuesDAO.agreeAnswer(aid);
	}
	public List<Answer> loadAnswers(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Answer> answers = issuesDAO.getAnswers(uid);
		transaction.commit();
		for (Answer answer : answers) {
			Issue issue = answer.getIssue();
			Student user = issue.getUser();
			Student s = new Student(user.getId(),user.getNickname(),user.getImageURL());
			issue.setUser(s);
			issue.setAnswers(null);
			answer.setIssue(issue);
		}
		return answers;
	}
	public Set<Issue> loadQuesions(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, id);
		Set<Issue> issues = s.getIssues();
		System.out.println(issues);
		transaction.commit();
		for (Issue issue : issues) {
			issue.setUser(null);
			issue.setAnswers(null);
		}
		return issues;
	}
	public void deleteAnswer(int aid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Answer answer = session.load(Answer.class, aid);
		Issue issue = answer.getIssue();
		issue.setAnswerNumber(issue.getAnswers().size()-1);
		session.delete(answer);
		transaction.commit();
	}
	public void deleteIssue(int iid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Issue issue = session.load(Issue.class, iid);
		session.delete(issue);
		transaction.commit();
	}
	
	public Issue getIssue(int iid) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Issue issue = session.load(Issue.class, iid);
		transaction.commit();
		Issue issue2 = new Issue(issue.getId(), issue.getTitle(), 
				issue.getContent(), issue.getUser(), issue.isAnonymous(), issue.getAnswerNumber());
		return issue2;
	}
}
