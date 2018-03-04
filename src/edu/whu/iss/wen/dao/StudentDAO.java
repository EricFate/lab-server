package edu.whu.iss.wen.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalCourseLearning;
import edu.whu.iss.bean.TotalMessageRecord;
import edu.whu.iss.wen.bean.DayStudyInfo;
import edu.whu.iss.wen.utils.HibernateUtil;

public class StudentDAO {
	// 获取学生课程学习情况
	public Set<CourseLearning> getCourseLearningInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<CourseLearning> set = new HashSet<CourseLearning>();
		try {

			Student s = session.get(Student.class, id);
			System.out.println(s.getLearnings());
			set = s.getLearnings();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return set;
	}

	// 获取学生课程总学习情况
	public Set<TotalCourseLearning> getTotalCourseLearningInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<TotalCourseLearning> set = new HashSet<TotalCourseLearning>();
		try {
			Student s = session.get(Student.class, id);
			System.out.println(s.getLearnings());
			set = s.getTotalLearnings();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return set;
	}

	// 获取学生发送消息情况
	public Set<MessageRecord> getMessageRecordInDAO(String uid) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<MessageRecord> list = null;
		try {
			String hql = "from MessageRecord messageRecord where messageRecord.fromUid = :fromUid";
			list = session.createQuery(hql).setString("fromUid", uid).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return (Set<MessageRecord>) list;
	}

	// 获取学生发送消息总情况
	public Set<TotalMessageRecord> getTotalMessageRecordInDAO(String uid) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<TotalMessageRecord> list = null;
		try {
			String hql = "from TotalMessageRecord tmr where tmr.fromUid = :fromUid";
			list = session.createQuery(hql).setString("fromUid", uid).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return (Set<TotalMessageRecord>) list;
	}

	// 获取学生学习总情况
	public DayStudyInfo getDayStudyInfoInDAO(Date time, int id, String uid) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String clHql = "from CourseLearning cl where cl.time = :time and cl.student=:id";
		String queHql = "from Issue que where que.time = :time and que.user=:id";
		String ansHql = "from Answer ans where ans.time = :time and ans.uno=:uid";
		String mesHql = "from MessageRecord mr where mr.time = :time and mr.fromUid=:uid";
		DayStudyInfo dsi = new DayStudyInfo();
		try {
			List<CourseLearning> cl = session.createQuery(clHql).setDate("time", time).setInteger("id", id).list();
			List<Issue> que = session.createQuery(queHql).setDate("time", time).setInteger("id", id).list();
			List<Answer> ans = session.createQuery(ansHql).setDate("time", time).setString("uid", uid).list();
			List<MessageRecord> mr = session.createQuery(mesHql).setDate("time", time).setString("uid", uid).list();
			dsi.setLearning(cl);
			dsi.setIssues(que);
			dsi.setAnswers(ans);
			dsi.setMessages(mr);
			dsi.setTime(time);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return dsi;
	}

	// 获得单个提问
	public Issue getSingleIssueInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Issue i = null;
		try {
			i = session.get(Issue.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return i;
	}

	// 获得单个回答
	public Answer getSingleAnswerInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Answer a = null;
		try {
			a = session.get(Answer.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();

		}
		return a;
	}
}
