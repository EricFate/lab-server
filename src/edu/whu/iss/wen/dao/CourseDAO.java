package edu.whu.iss.wen.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.Major;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.CourseResult;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.utils.HibernateUtil;

public class CourseDAO {
	private final static String GET_MAJOR_HQL = "from Major where title = :title";

	// 课程上传
	public CourseResult courseUploadInDAO(Course course) {
		CourseResult courseResult = new CourseResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			int id = (Integer) session.save(course);
			String str = "t" + course.getTeacher().getId();
			RongCloudUtils.getInstance().group.create(new String[] { str }, "g" + id, course.getName());
			ChatGroup group = new ChatGroup(course.getName(), course);
			course.setChatGroup(group);
			session.save(group);
			courseResult.setResultCode(1);
			courseResult.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return courseResult;
	}

	// 获取所有课程信息
	public Set<Course> getAllCourseInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Course> courseSet = new HashSet<Course>();
		try {
			Teacher teacher = session.get(Teacher.class, id);
			courseSet = teacher.getCourses();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return courseSet;
	}

	// 获取单个课程信息
	public Course getSingleCourseInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course course = new Course();
		try {
			course = session.get(Course.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return course;
	}

	// 修改课程信息
	public UniversalResult courseInfoReviseInDAO(String title, Map<String, String[]> map, int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Course course = session.get(Course.class, id);
			Major s = (Major) session.createQuery(GET_MAJOR_HQL).setString("title", title).uniqueResult();
			if (s == null) {
				s = new Major();
				s.setTitle(title);
				session.save(s);
			}
			BeanUtils.populate(course, map);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	// 上传封面
	public UniversalResult coverUploadInDAO(String id, String coverURL) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {

			// String hql="update Course course set course.coverURL=:coverURL
			// where course.id=:id";
			// Query queryupdate=session.createQuery(hql).setString("coverURL",
			// coverURL).setString("id",id);
			// int ret=queryupdate.executeUpdate();
			Course c = session.get(Course.class, Integer.parseInt(id));
			c.setCoverURL(coverURL);
			c.getChatGroup().setImageURL(coverURL);

			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	// 课程章节数加一
	public UniversalResult chapterNumberPlusInDAO(int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Course course = session.get(Course.class, id);
			course.setChapterNumber(course.getChapterNumber() + 1);

			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	//删除课程
	public UniversalResult deleteCourseInDAO(int id){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Course l = session.get(Course.class, id);
			l.getTeacher().getCourses().remove(l);
			l.setTeacher(null);
			session.delete(l);
 			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	//上传公告
	public UniversalResult noticeUploadInDAO(Notice notice/*,int id*/){
		UniversalResult ur = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			//Course course=session.get(Course.class,id);
			//notice.setCourse(course);
			session.save(notice);
			ur.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return ur;
	}
	
	//获取公告
	public Set<Notice> getNoticesInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Notice> s = new HashSet<Notice>();
		try {
			s = session.get(Course.class, id).getChatGroup().getNotices();
			System.out.println(s.size()+"啊啊啊啊啊啊"+session.get(Course.class, id).getChatGroup().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return s;
	}
	
	
	//删除公告
	public UniversalResult deleteNoticeInDAO(int id){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Notice n = session.get(Notice.class, id);
			session.delete(n);
 			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}

}
