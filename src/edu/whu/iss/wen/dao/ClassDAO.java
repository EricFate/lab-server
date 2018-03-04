package edu.whu.iss.wen.dao;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.AddClassRequest;
import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Issue;
import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Student;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.StudentAndStudy;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.utils.HibernateUtil;

public class ClassDAO {
	// ��ȡ���п�ʱ��Ϣ
	public Set<AdminClass> getClassInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<AdminClass> set = new HashSet<AdminClass>();
		try {
			Course course = session.get(Course.class, id);
			set = course.getClasses();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			transaction.commit();
		}
		return set;
	}

	// ��ȡѧ���б�
	public Set<StudentAndStudy> getStudentListInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Student> stuSet = new HashSet<Student>();
		Set<StudentAndStudy> set = new HashSet<StudentAndStudy>();
		try {
			AdminClass ac = session.get(AdminClass.class, id);
			stuSet = ac.getStudents();
			for(Student student:stuSet){
				int stuId=student.getId();
				StudentAndStudy sas=new StudentAndStudy();
				String hql = "from CourseLearning cl where cl.time = :time and cl.student= :id";
				List<CourseLearning> cl= session.createQuery(hql).setDate("time", new Date()).setString("id",String.valueOf(student.getId())).list();
				if(cl.size()==1)
					sas.setDuration(cl.get(0).getDuration());
				else {
					sas.setDuration(0);
				}
				sas.setId(stuId);
				sas.setImageURL(student.getImageURL());
				sas.setName(student.getRealname());
				String hql2 = "from Issue i where i.time = :time and i.user= :id";
				List<Issue> i= session.createQuery(hql2).setDate("time", new Date()).setString("id",String.valueOf(student.getId())).list();
				sas.setIssueNum(i.size());
				
				String hql3 = "from Answer a where a.time = :time and a.uno= :id";
				List<Answer> a= session.createQuery(hql3).setDate("time", new Date()).setString("id","s"+String.valueOf(student.getId())).list();
				sas.setAnswerNum(a.size());
				
				String hql4 = "from MessageRecord mr where mr.time = :time and mr.fromUid= :id";
				List<MessageRecord> mr= session.createQuery(hql4).setDate("time", new Date()).setString("id","s"+String.valueOf(student.getId())).list();
				
				sas.setGender(student.getGender());
				sas.setMessageNum(mr.size());
				sas.setSignature(student.getSignature());
				set.add(sas);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			transaction.commit();
		}
		return set;
	}
	
	public Student getStudentInDAO(int id){
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student student = null;
		try {
			student = session.get(Student.class, id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transaction.commit();
		}
		return student;
	}

	// �����༶
	public ForIdResult createClassInDAO(AdminClass ac) {
		ForIdResult fir = new ForIdResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			int id = (Integer) session.save(ac);
			String str = "m" + ac.getTeacher().getId();
			RongCloudUtils.getInstance().group.create(new String[] { str }, "g" + id,
					ac.getSchool() + ac.getGrade() + ac.getsNumber());
			ChatGroup group = new ChatGroup(ac.getSchool() + ac.getGrade() + ac.getsNumber(), ac);
			ac.setChatGroup(group);
			fir.setResultCode(1);
			fir.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
			fir.setResultCode(0);
		} finally {
			transaction.commit();
		}
		return fir;
	}

	// ����id��ȡ�༶
	public AdminClass getAdminClassInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AdminClass adminClass = new AdminClass();
		try {
			adminClass = session.get(AdminClass.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return adminClass;
	}

	// ɾ���༶
	public UniversalResult deleteClassInDAO(int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			AdminClass ac = session.get(AdminClass.class, id);
			session.delete(ac);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	// ��ȡ����
	public Set<Notice> getNoticesInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Notice> notices = new HashSet<Notice>();
		try {
			notices = session.get(AdminClass.class, id).getChatGroup().getNotices();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return notices;
	}
	
	//��ȡ�༶ѧ������
	public Set<AddClassRequest> getRequestsInDAO(int id){
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<AddClassRequest> requests = new HashSet<AddClassRequest>();
		try {
			requests = session.get(AdminClass.class, id).getRequests();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			transaction.commit();
		}
		return requests;
	}
	
	//��Ӱ༶ѧ��
	public UniversalResult addStudentInDAO(int classId, int studentId){
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		UniversalResult result = new UniversalResult();
		try {
			AdminClass ac = session.get(AdminClass.class, classId);
			Student student = session.get(Student.class, studentId);
			ac.getStudents().add(student);
			result.setResultCode(1);
		}catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(0);
			// TODO: handle exception
		} finally {
			// TODO: handle finally clause
			transaction.commit();
		}
		return result;
	}
	
	public UniversalResult deleteStudentReqestInDAO(int classId, int studentId){
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		UniversalResult result = new UniversalResult();
		try {
			String SQLString = "delete from addclassrequest where sid="+studentId+" and acid="+classId;
			session.createSQLQuery(SQLString).executeUpdate();
			transaction.commit();
			result.setResultCode(1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setResultCode(0);
		} finally {
			// TODO: handle finally clause
			
		}
		return result;
		
	}
}
