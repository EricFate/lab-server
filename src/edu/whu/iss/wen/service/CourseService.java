package edu.whu.iss.wen.service;

import java.util.Map;
import java.util.Set;


import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.dao.CourseDAO;
import edu.whu.iss.wen.result.CourseResult;
import edu.whu.iss.wen.result.UniversalResult;


public class CourseService {
	CourseDAO courseDAO = new CourseDAO();

	public CourseResult courseUploadInService(Course course) {
		return courseDAO.courseUploadInDAO(course);
	}

	// ��ȡ���пγ���Ϣ
	public Set<Course> getAllCourseInfoInService(int id) {
		Set<Course> courseSet = courseDAO.getAllCourseInfoInDAO(id);
		// ��ֹ��ѭ��
		for (Course course : courseSet) {
			course.setTeacher(null);
			course.setChapters(null);
			course.setMajor(null);
			course.setStudents(null);
			course.setRanks(null);
			course.setChatGroup(null);
			course.setClasses(null);
			course.setLearnings(null);
			course.setTotalLearnings(null);
			course.setExerciseCatagorys(null);
			
		}
		return courseSet;
	}

	// ��ȡ�����γ���Ϣ
	public Course getSingleCourseInfoInService(int id) {
		Course course = courseDAO.getSingleCourseInfoInDAO(id);
		// ��ֹ��ѭ��
		Teacher t=new Teacher(course.getTeacher().getId(),course.getTeacher().getRealname());
		course.setTeacher(t);
		course.setChapters(null);
		course.setStudents(null);
		course.setRanks(null);
		course.getMajor().setCourses(null);
		course.setChatGroup(null);
		course.setClasses(null);
		course.setLearnings(null);
		course.setTotalLearnings(null);
		course.setExerciseCatagorys(null);
		course.setClasses(null);
		return course;
	}
	
	//�޸Ŀγ���Ϣ
	public UniversalResult courseInfoReviseInService(String majorTitle,Map<String, String[]> map,int id){
		UniversalResult universalResult=courseDAO.courseInfoReviseInDAO(majorTitle,map, id);
		return universalResult; 
	}
	
	//�ϴ�����
	public UniversalResult coverUploadInService(String id,String coverURL){
		UniversalResult universalResult=courseDAO.coverUploadInDAO(id, coverURL);
		return universalResult;
	}
	//�γ��½�����һ
	public UniversalResult chapterNumberPlusInService(int id){
		UniversalResult result=courseDAO.chapterNumberPlusInDAO(id);
		return result;
	}
	
	//ɾ����ʱ
	public UniversalResult deleteCourseInService(int id){
		return courseDAO.deleteCourseInDAO(id);
	}
	
	//�ϴ�����
	public UniversalResult noticeUploadInService(Notice notice,int id){
		return courseDAO.noticeUploadInDAO(notice);
	}
	
	//��ȡ����
	public Set<Notice> getNoticesInService(int id) {
		Set<Notice> s=courseDAO.getNoticesInDAO(id);
		for(Notice n:s){
			n.setChatGroup(null);
		}
		return s;
	}
	
	//ɾ������
	public UniversalResult deleteNoticeInService(int id){
		return courseDAO.deleteNoticeInDAO(id);
	}
}
