package edu.whu.iss.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.Info;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.wen.bean.Teacher;

public class IssuesDAO {
	private final static String GET_QUESTIONS_HQL = "select new Issue(i.id,i.title,i.content,i.user,i.anonymous,i.answerNumber) from Issue i";
	private final static String GET_ANSWER_HQL = "from Answer where uno=:uid order by time desc";
	public void saveAnswer(Answer answer){
		Session session = HibernateUtils.getCurrentSession();
		session.save(answer);
	}

	public Set<Answer> getAnswers(int iid) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Issue issue = session.get(Issue.class, iid);
		issue.setAnswerNumber(issue.getAnswers().size());
		Set<Answer> answers = issue.getAnswers();
		for (Answer answer : answers) {
			String uno = answer.getUno();
			Info info = null;
			int id = Integer.parseInt(uno.substring(1, uno.length()));
			switch (uno.charAt(0)) {
			case 's':
				Student s =  session.get(Student.class, id);
				info = new Info(uno,s.getNickname(),s.getImageURL());
				break;
			case 't':
				Teacher t = session.get(Teacher.class, id);
				info = new Info(uno,t.getRealname(),t.getImageURL());
				break;
			case 'c':
				CollegeStudent c = session.get(CollegeStudent.class, id);
				info = new Info(uno,c.getNickname(),c.getImageURL());
				break;
			case 'a':
				
				break;
			default:
				break;
			} 
			answer.setAnswerer(info);
		}
		transaction.commit();
		for (Answer answer : answers)
			answer.setIssue(null);
		return answers;
	}
	public List<Issue> getIssues(int start) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(GET_QUESTIONS_HQL).setFirstResult(start).setMaxResults(10);
		List<Issue> list = query.list();
		transaction.commit();
//		session.close();
		for (Issue issue : list) {
			issue.setAnswers(null);
			Student user = issue.getUser();
			user = new Student(user.getId(), user.getNickname(),user.getImageURL());
			issue.setUser(user);
		}
		return list;
	}

	public void saveIssue(Issue question) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		User user = getUser(username);
//		question.setUser(user);
		session.save(question);
//		transaction.commit();
//		session.close();
	}





	public void agreeAnswer(int aid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Answer answer = session.get(Answer.class, aid);
		answer.setAgree(answer.getAgree()+1);
		transaction.commit();
//		session.close();
	}

	public List<Answer> getAnswers(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		List<Answer> answers=  session.createQuery(GET_ANSWER_HQL).setString("uid", uid).list();
		return answers;
	}
}
