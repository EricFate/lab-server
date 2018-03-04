package edu.whu.iss.wen.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalCourseLearning;
import edu.whu.iss.bean.TotalMessageRecord;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.DayStudyInfo;
import edu.whu.iss.wen.dao.StudentDAO;

public class StudentService {
	StudentDAO studentDAO = new StudentDAO();

	// 获取课程学习情况
	public Set<CourseLearning> getCourseLearningInService(int id) {
		Set<CourseLearning> s = studentDAO.getCourseLearningInDAO(id);
		for (CourseLearning cl : s) {
			String name = cl.getCourse().getName();
			int courseId = cl.getCourse().getId();
			cl.setStudent(null);
			Course course = new Course(courseId, name);
			cl.setCourse(course);
			cl.getCourse().setName(name);
		}
		return s;
	}

	// 获取总课程学习情况
	public Set<TotalCourseLearning> getTotalCourseLearningInDAO(int id) {
		return studentDAO.getTotalCourseLearningInDAO(id);
	}

	// 获取发送消息情况
	public Set<MessageRecord> getMessageRecordInService(String uid) {
		return studentDAO.getMessageRecordInDAO(uid);
	}

	// 获取发送消息总情况
	public Set<TotalMessageRecord> getToalMessageRecordInService(String uid) {
		return studentDAO.getTotalMessageRecordInDAO(uid);
	}

	public DayStudyInfo getDayStudyInfoInService(Date time, int id, String uid) {
		DayStudyInfo dsi = studentDAO.getDayStudyInfoInDAO(time, id, uid);

		List<CourseLearning> cl = dsi.getLearning();
		List<Issue> issue = dsi.getIssues();
		List<Answer> answer = dsi.getAnswers();
		for (CourseLearning c : cl) {
			Course course = new Course(c.getCourse().getId(), c.getCourse()
					.getName());
			c.setCourse(course);
			c.setStudent(null);
		}
		for (Issue i : issue) {
			i.setAnswers(null);
			i.setUser(null);
		}
		for (Answer a : answer) {
			Issue iss = new Issue(a.getIssue().getId(), a.getIssue().getTitle());
			a.setIssue(iss);
		}
		dsi.setLearning(cl);
		return dsi;
	}

	// 获得单个提问
	public Issue getSingleIssueInService(int id) {
		Issue i = studentDAO.getSingleIssueInDAO(id);
		Student s = new Student(i.getUser().getId(), i.getUser().getNickname(),
				i.getUser().getImageURL());
		i.setUser(s);
		i.setAnswers(null);
		return i;
	}

	// 获得单个回答
	public Answer getSingleAnswerInService(int id) {
		Answer answer = studentDAO.getSingleAnswerInDAO(id);

		Issue issue = answer.getIssue();
		Student user = issue.getUser();
		Student s = new Student(user.getId(), user.getNickname(),
				user.getImageURL());
		issue.setUser(s);
		issue.setAnswers(null);
		answer.setIssue(issue);

		return answer;
	}
}
