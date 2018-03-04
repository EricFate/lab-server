package edu.whu.iss.wen.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.utils.HibernateUtil;

public class ChapterDAO {
	// 章节上传
	public UniversalResult chapterUploadInDAO(Chapter chapter) {
		UniversalResult chapterUploadResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(chapter);	
			chapterUploadResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transaction.commit();
			
		}
		return chapterUploadResult;
	}

	// 获取所有章节信息
	public Set<Chapter> getAllChapterInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Chapter> chapterSet = new HashSet<Chapter>();
		try{
		Course course = session.get(Course.class, id);
		chapterSet = course.getChapters();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			transaction.commit();
		}
		return chapterSet;
	}

	// 获取单个章节信息
	public Chapter getSingleChapterInfoInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Chapter chapter =new Chapter();
		try{
			chapter = session.get(Chapter.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			transaction.commit();
		}
		return chapter;
	}

	// 修改章节信息
	public UniversalResult chapterInfoReviseInDAO(Map<String, String[]> map,
			int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Chapter chapter = session.get(Chapter.class, id);
			BeanUtils.populate(chapter, map);		
			universalResult.setResultCode(1);		
		} catch (Exception e) {
			e.printStackTrace();
			return universalResult;
		}finally{
			transaction.commit();
		}
		return universalResult;
	}

	// 章节课时数加一
	public UniversalResult lessonNumberPlusInDAO(int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Chapter chapter = session.get(Chapter.class, id);
			chapter.setLessonNumber(chapter.getLessonNumber() + 1);
			chapter.setIsSigned(1);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transaction.commit();
		}
		return universalResult;
	}
	
	//删除章节
	public UniversalResult deleteChapterInDAO(int id){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Chapter l = session.get(Chapter.class, id);
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
