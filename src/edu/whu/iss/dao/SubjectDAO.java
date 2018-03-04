package edu.whu.iss.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Major;
import edu.whu.iss.bean.TotalCourseLearning;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.wen.bean.Course;

public class SubjectDAO {
	private static SubjectDAO instance = new SubjectDAO();
	public static SubjectDAO getInstance() {
		return instance;
	}
	private static final String GET_MY_COURSE_HQL = "select new TotalCourseLearning(c.id,c.duration,c.course)" +
			"from TotalCourseLearning c join c.student s where s.id=:id order by c.id desc";
	private static final String GET_ALL_SUBJECTS_HQL = "from Major";
	private static final String GET_COURSE_LEARNING_BY_INFO_HQL = "from CourseLearning where sno=:sid and cno=:cid and time=:time";
	private static final String GET_TOTAL_COURSE_LEARNING_BY_INFO_HQL = "from TotalCourseLearning where sno=:sid and cno=:cid";
	public void saveSubject(Course subject) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		session.save(subject);
	}
	public Course getSubject(int sid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Course subject = session.get(Course.class, sid);
		return subject;
	}
	public void getSubjectOfCategory(int cid){
		Session session = HibernateUtils.getCurrentSession();
		
	}
	public List<Major> getAllSubject(){
		Session session = HibernateUtils.getCurrentSession();
		List<Major> subjects = session.createQuery(GET_ALL_SUBJECTS_HQL).list();
		return subjects;
	}
	public List<TotalCourseLearning> getMySubject(int uid, int start) {
		Session session = HibernateUtils.getCurrentSession();
		List<TotalCourseLearning> list = session.createQuery(GET_MY_COURSE_HQL).setInteger("id", uid).setFirstResult(start).setMaxResults(10).list();
		return list;
	}
	public CourseLearning getCourseLearningByInfo(int sid, int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		CourseLearning learning = (CourseLearning) session.createQuery(GET_COURSE_LEARNING_BY_INFO_HQL).setInteger("sid", sid)
		.setInteger("cid", cid).setDate("time", new Date()).uniqueResult();
		return learning;
	}
	public TotalCourseLearning getTotalCourseLearningByInfo(int sid, int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		TotalCourseLearning learning = (TotalCourseLearning) session.createQuery(GET_TOTAL_COURSE_LEARNING_BY_INFO_HQL).setInteger("sid", sid)
				.setInteger("cid", cid).uniqueResult();
		return learning;
	}


}
