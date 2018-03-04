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

	// ��ȡ�༶�б�
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
	
	//��ȡѧ���б�
	public Set<StudentAndStudy> getStudentListInService(int id){
		return classDAO.getStudentListInDAO(id);
	}
	
	//��ȡ����ѧ��
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
	
	//�����༶
	public ForIdResult createClassInService(AdminClass ac) {
		return classDAO.createClassInDAO(ac);
	}
	
	//ɾ���༶
	public UniversalResult deleteClassInService(int id){
		return classDAO.deleteClassInDAO(id);
	}
	
	//��ȡ�༶
	public AdminClass getAdminClass(int id){
		return classDAO.getAdminClassInDAO(id);
	}
	
	//��ȡ�༶����
	public Set<Notice> getNotices(int id){
		Set<Notice> set= classDAO.getNoticesInDAO(id);
		for(Notice notice:set){
			notice.setChatGroup(null);
		}
		return set;
	}
	
	//��ȡ�༶ѧ����������
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
	
	//��Ӱ༶ѧ��
	public UniversalResult addStudent(int classId, int studentId){
		return classDAO.addStudentInDAO(classId, studentId);
	}
	
	//ɾ���Ӱ�����
	public UniversalResult deleteStudentRequest(int classId, int studentId){
		return classDAO.deleteStudentReqestInDAO(classId, studentId);
	}
	
}
