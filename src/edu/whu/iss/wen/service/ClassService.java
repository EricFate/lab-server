package edu.whu.iss.wen.service;

import java.util.HashSet;
import java.util.Set;

import edu.whu.iss.bean.AddClassRequest;
import edu.whu.iss.bean.Student;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.StudentAndStudy;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.dao.ClassDAO;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.UniversalResult;

public class ClassService {
	ClassDAO classDAO = new ClassDAO();

	// 获取班级列表
	public Set<AdminClass> getClassInService(int id) {
		Set<AdminClass> set=classDAO.getClassInDAO(id);
		for(AdminClass a :set){
			a.setCourses(null);
			a.setCollegeStudents(null);
			a.setStudents(null);
			a.setTeacher(null);
			a.setChatGroup(null);
			a.setNotices(null);
			a.setRequests(null);
			
		}
		return set;
	}
	
	//获取学生列表
	public Set<StudentAndStudy> getStudentListInService(int id){
		return classDAO.getStudentListInDAO(id);
	}
	
	//获取单个学生
	public Student getStudent(int id){
		Student s = classDAO.getStudentInDAO(id);
		s.setAdminClass(null);
		s.setCollegeStudent(null);
		s.setTotalMessages(null);
		s.setTotalLearnings(null);
		s.setCourses(null);
		s.setExCateDetails(null);
		s.setExDetails(null);
		s.setIssues(null);
		s.setLearnings(null);
		s.setRanks(null);
		return s;
	}
	
	//创建班级
	public ForIdResult createClassInService(AdminClass ac) {
		return classDAO.createClassInDAO(ac);
	}
	
	//删除班级
	public UniversalResult deleteClassInService(int id){
		return classDAO.deleteClassInDAO(id);
	}
	
	//获取班级
	public AdminClass getAdminClass(int id){
		return classDAO.getAdminClassInDAO(id);
	}
	
	//获取班级公告
	public Set<Notice> getNotices(int id){
		Set<Notice> set= classDAO.getNoticesInDAO(id);
		for(Notice notice:set){
			notice.setChatGroup(null);
		}
		return set;
	}
	
	//获取班级学生加入请求
	public Set<AddClassRequest> getRequests(int id){
		Set<AddClassRequest> requests = classDAO.getRequestsInDAO(id);
		for(AddClassRequest request:requests){
			request.setAdminClass(null);
			Student s = request.getStudent();
			Student student = new Student(s.getId(),s.getRealname(), s.getSignature(),s.getImageURL());
			request.setStudent(student);
		}
		return requests;
	}
	
	//添加班级学生
	public UniversalResult addStudent(int classId, int studentId){
		return classDAO.addStudentInDAO(classId, studentId);
	}
	
	//删除加班请求
	public UniversalResult deleteStudentRequest(int classId, int studentId){
		return classDAO.deleteStudentReqestInDAO(classId, studentId);
	}
	
}
