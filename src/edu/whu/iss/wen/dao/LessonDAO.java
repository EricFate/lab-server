package edu.whu.iss.wen.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.GetVideoResult;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.utils.HibernateUtil;

public class LessonDAO {
	// 课时上传
	public ForIdResult lessonUploadInDAO(Lesson lesson) {
		ForIdResult lessonUploadResult = new ForIdResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			int i = (Integer) session.save(lesson);
			lessonUploadResult.setResultCode(1);
			lessonUploadResult.setId(i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return lessonUploadResult;
	}

	// 获取所有课时信息
	public Set<Lesson> getLessonInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Lesson> lessonSet = new HashSet<Lesson>();
		try {
			Chapter chapter = session.get(Chapter.class, id);
			lessonSet = chapter.getLessons();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return lessonSet;
	}

	// 获取该课程的所有课时信息
	public List<Set<Lesson>> getAllLessonInfoInDAO(List<Integer> list) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Set<Lesson>> ll = new ArrayList<Set<Lesson>>();
		try {
			for (int i : list) {
				Chapter chapter = session.get(Chapter.class, i);
				Set<Lesson> l = chapter.getLessons();
				ll.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return ll;
	}

	// 获取单个课时信息
	public Lesson getSingleLessonInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Lesson lesson = new Lesson();
		try {
			lesson = session.get(Lesson.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return lesson;
	}

	// 修改课时信息
	public UniversalResult lessonInfoReviseInDAO(Map<String, String[]> map, int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Lesson lesson = session.get(Lesson.class, id);

			BeanUtils.populate(lesson, map);

			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	public GetVideoResult getLessonVideoInDAO(int id) {
		GetVideoResult getVideoResult = new GetVideoResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Lesson lesson = session.get(Lesson.class, id);
			getVideoResult.setVideoURL(lesson.getVideoURL());
			getVideoResult.setVideoImageURL(lesson.getVideoImageURL());
			getVideoResult.setResultCode(1);
			getVideoResult.setLessonName(lesson.getLessonName());
		} catch (Exception e) {
			e.printStackTrace();
			getVideoResult.setResultCode(0);
		} finally {
			transaction.commit();
		}
		return getVideoResult;
	}

	public UniversalResult videoImageUploadInDAO(int id, String videoImageURL) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Lesson l = session.get(Lesson.class, id);
			l.setVideoImageURL(videoImageURL);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	//删除课时
	public UniversalResult deleteLessonInDAO(int id){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Lesson l = session.get(Lesson.class, id);
			Chapter c=l.getChapter();
			c.setLessonNumber(c.getLessonNumber()-1);
			if(c.getLessonNumber()==0){
				c.setIsSigned(0);
				Course course=c.getCourse();
				course.setChapterNumber(course.getChapterNumber()-1);
			}
			session.delete(l);
 			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}
}
