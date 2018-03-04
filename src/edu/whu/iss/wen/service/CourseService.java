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

	// 获取所有课程信息
	public Set<Course> getAllCourseInfoInService(int id) {
		Set<Course> courseSet = courseDAO.getAllCourseInfoInDAO(id);
		// 防止死循环
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

	// 获取单个课程信息
	public Course getSingleCourseInfoInService(int id) {
		Course course = courseDAO.getSingleCourseInfoInDAO(id);
		// 防止死循环
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
	
	//修改课程信息
	public UniversalResult courseInfoReviseInService(String majorTitle,Map<String, String[]> map,int id){
		UniversalResult universalResult=courseDAO.courseInfoReviseInDAO(majorTitle,map, id);
		return universalResult; 
	}
	
	//上传封面
	public UniversalResult coverUploadInService(String id,String coverURL){
		UniversalResult universalResult=courseDAO.coverUploadInDAO(id, coverURL);
		return universalResult;
	}
	//课程章节数加一
	public UniversalResult chapterNumberPlusInService(int id){
		UniversalResult result=courseDAO.chapterNumberPlusInDAO(id);
		return result;
	}
	
	//删除课时
	public UniversalResult deleteCourseInService(int id){
		return courseDAO.deleteCourseInDAO(id);
	}
	
	//上传公告
	public UniversalResult noticeUploadInService(Notice notice,int id){
		return courseDAO.noticeUploadInDAO(notice);
	}
	
	//获取公告
	public Set<Notice> getNoticesInService(int id) {
		Set<Notice> s=courseDAO.getNoticesInDAO(id);
		for(Notice n:s){
			n.setChatGroup(null);
		}
		return s;
	}
	
	//删除公告
	public UniversalResult deleteNoticeInService(int id){
		return courseDAO.deleteNoticeInDAO(id);
	}
}
