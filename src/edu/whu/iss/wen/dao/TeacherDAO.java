package edu.whu.iss.wen.dao;

import io.rong.models.TokenReslut;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.AddClassRequest;
import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Exercise;
import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.bean.ExerciseSubject;
import edu.whu.iss.wen.bean.FillInBlankExercise;
import edu.whu.iss.wen.bean.Knowledge;
import edu.whu.iss.wen.bean.KnowledgeRelationship;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.bean.MultipleChoicesExercise;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.utils.HibernateUtil;

public class TeacherDAO {

	// ע��
	public ForIdResult registerTeacherInDAO(Teacher teacher) {
		// //Configuration�ӿ�:�������ò�����Hibernate
		// Configuration cfg = new Configuration().configure();
		// //SessionFactory�ӿ�:�����ʼ��Hibernate
		// SessionFactory factory = cfg.buildSessionFactory();
		// //Session�ӿ�:����־û������CRUD����
		// Session session = factory.openSession();
		// //Transaction�ӿ�:��������
		// Transaction transaction = session.beginTransaction();
		// //Query�ӿں�Criteria�ӿ�:����ִ�и������ݿ��ѯ
		// session.save(teacher);
		// transaction.commit();
		// session.close();
		ForIdResult registerResult = new ForIdResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {

			int id = (Integer) session.save(teacher);
			String uid = "t" + id;
			RosterGroup rosterGroup = new RosterGroup("δ����", uid);
			session.save(rosterGroup);
			TokenReslut token = RongCloudUtils.getInstance().user.getToken("t" + id, teacher.getRealname(), "");
			teacher.setToken(token.getToken());

			registerResult.setResultCode(1);
			registerResult.setId(id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return registerResult;
	}

	// �ж��û����Ƿ�Ψһ
	public UniversalResult isUsernameUniqueInDAO(String username) {
		UniversalResult result = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from Teacher teacher where teacher.username = :username";
			List<Teacher> list = session.createQuery(hql).setString("username", username).list();
			if (list.size() == 0) {
				result.setResultCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return result;
	}

	// ��¼
	public List<Teacher> loginInTeacherInDAO(String username, String password) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			String hql = "from Teacher teacher where teacher.username = :username";
			list = session.createQuery(hql).setString("username", username).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// ������ѭ��
			for (Teacher teacher : list) {
				teacher.setCourses(null);
				Set<AdminClass> adminClasses = teacher.getClasses();
				for (AdminClass adminClass : adminClasses) {
					adminClass.setTeacher(null);
					adminClass.setChatGroup(null);
					Set<Student> set=new HashSet<Student>();
					for(Student student:adminClass.getStudents()){
						Student student2=new Student();
						set.add(student2);		
					}
					adminClass.setStudents(set);
					Set<CollegeStudent> cset=new HashSet<CollegeStudent>();
					for(CollegeStudent student:adminClass.getCollegeStudents()){
						CollegeStudent student2=new CollegeStudent();
						cset.add(student2);		
					}
					adminClass.setCollegeStudents(cset);
					adminClass.setNotices(null);
					adminClass.setCourses(null);
					Set<AddClassRequest> requests = adminClass.getRequests();
					for(AddClassRequest request:requests){
						request.setAdminClass(null);
						Student s = request.getStudent();
						Student student = new Student(s.getId(),s.getRealname(), s.getSignature(),s.getImageURL());
						request.setStudent(student);
					}
				}
			}
		}
		return list;
	}

	// ͨ��token�õ��ý�ʦ�������ڴ����༶
	public List<Teacher> getTeacherByToken(String token) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			String hql = "from Teacher teacher where teacher.token = :token";
			list = session.createQuery(hql).setString("token", token).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ������ѭ��
			for (Teacher teacher : list) {
				teacher.setCourses(null);
			}
		}
		return list;
	}

	// �ϴ�ͷ��
	public UniversalResult imageUploadInDAO(String id, String imageURL) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "update Teacher teacher set teacher.imageURL=:imageURL where teacher.id=:id";
			Query queryupdate = session.createQuery(hql).setString("imageURL", imageURL).setString("id", id);
			int ret = queryupdate.executeUpdate();
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	//�ϴ������ͼƬ
	public UniversalResult fillInBlankExerciseImageUpload(String id, String imageURL){
		System.out.println("Method invoked.");
		UniversalResult universalResult = new UniversalResult();
		System.out.println("Begin session.");
		Session session = HibernateUtil.getCurrentSession();
		System.out.println("Begin transaction.");
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("Exercise update imageURL begins.");
			String hql = "update FillInBlankExercise e set e.imageURL=:imageURL where e.id=:id";
			Query queryupdate = session.createQuery(hql).setString("imageURL", imageURL).setString("id", id);
			int ret = queryupdate.executeUpdate();
			System.out.println("Exercise update imageURL finished.");
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	//�ϴ�ѡ����ͼƬ
	public UniversalResult multipleChoicesExerciseImageUpload(String id, String imageURL){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "update MultipleChoicesExercise e set e.imageURL=:imageURL where e.id=:id";
			Query queryupdate = session.createQuery(hql).setString("imageURL", imageURL).setString("id", id);
			int ret = queryupdate.executeUpdate();
			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	// �޸ĸ�����Ϣ
	public UniversalResult teacherInfoReviseInDAO(Map<String, String[]> map, int id) {
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {

			Teacher teacher = session.get(Teacher.class, id);
			BeanUtils.populate(teacher, map);

			universalResult.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return universalResult;
	}

	// �޸�����
	public UniversalResult passwordReviseInDAO(String uid, String password) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		UniversalResult ur = new UniversalResult();
		try {
			char c = uid.charAt(0);
			int id = Integer.parseInt(uid.substring(1));
			switch (c) {
			case 't':
				Teacher teacher = session.get(Teacher.class, id);
				teacher.setPassword(password);
				ur.setResultCode(1);
				break;
			case 's':
				Student student = session.get(Student.class, id);
				student.setPassword(password);
				ur.setResultCode(1);
				break;
			case 'c':
				CollegeStudent cs = session.get(CollegeStudent.class, id);
				cs.setPassword(password);
				ur.setResultCode(1);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();

		}
		return ur;
	}

	// ��ȡ��Ŀ
	public List<Exercise> getTestInDAO(String tid) {
		List<Exercise> list = new ArrayList<Exercise>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			// char t=tid.charAt(0);
			// int id=Integer.parseInt(tid.substring(1));
			// switch(t){
			// case('l'):
			// Lesson l = session.get(Lesson.class, id);
			// set=l.getExercises();
			// break;
			// case('c'):
			// Chapter c = session.get(Chapter.class, id);
			// break;
			// default:
			// Course course = session.get(Course.class, id);
			// }
			String hql = "from Exercise exercise";
			list = session.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return list;
	}

	// ��ȡ������Ŀ
	public Exercise getSingleExerciseInDAO(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Exercise ex = new Exercise();
		try {
			ex = session.get(Exercise.class, id);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			transaction.commit();
		}
		return ex;
	}

	// �ϴ���Ŀ
	public UniversalResult uploadTestInDAO(ExerciseCatagory ec, int id, int testType) {
		UniversalResult ur = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(ec);
			switch (testType) {
			case 0:
				Lesson lesson = session.get(Lesson.class, id);
				lesson.setExerciseCatagory(ec);
				break;
			case 1:
				Chapter chapter = session.get(Chapter.class, id);
				chapter.setExerciseCatagory(ec);
				break;
			}

			ur.setResultCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return ur;
	}

	// ��ȡ�⼯
	public ExerciseCatagory getExerciseCatagoryInDAO(char t, int id) {
		ExerciseCatagory ec = new ExerciseCatagory();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			switch (t) {
			case 'l':
				Lesson lesson = session.get(Lesson.class, id);
				ec = lesson.getExerciseCatagory();
				break;
			case 'h':
				Chapter chapter = session.get(Chapter.class, id);
				ec = chapter.getExerciseCatagory();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return ec;
	}

	// ��ȡһ���⼯����Ŀ
	public Set<Exercise> getExerciseInDAO(int id) {
		Set<Exercise> set = new HashSet<Exercise>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			ExerciseCatagory ec = session.get(ExerciseCatagory.class, id);
			set = ec.getExercises();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return set;
	}
	
	public UniversalResult verifyInfo(int flag, String username, String info){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Teacher> list = session.createQuery("from Teacher t where t.username=:username")
			.setString("username", username).list();
			if(list.size()>0){
				Teacher teacher = new Teacher();
				teacher.setEmail(list.get(0).getEmail());
				teacher.setPhone(list.get(0).getPhone());
				if(flag==0){
					if(teacher.getPhone().equals(info))
						universalResult.setResultCode(1);
					else {
						universalResult.setResultCode(0);
					}
				}else{
					if(teacher.getEmail().equals(info))
						universalResult.setResultCode(1);
					else {
						universalResult.setResultCode(0);
					}
				}
			}else{
				universalResult.setResultCode(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
			transaction.commit();
		}
		return universalResult;
	}
	
	public UniversalResult setNewPassword(String username, String password){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "update Teacher t set t.password = :password where t.username=:username";
			Query update = session.createQuery(hql).setString("password", password).setString("username", username);
			update.executeUpdate();
			universalResult.setResultCode(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			transaction.commit();
		}
		return universalResult;
	}
	
	public UniversalResult uploadKnowledge(String subject, int grade, String label, String description){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<ExerciseSubject> list = session.createQuery("from ExerciseSubject s where "
					+ "s.subject = :subject and s.grade = :grade")
					.setString("subject", subject).setInteger("grade", grade).list();
			//���꼶��Ŀ�Ѿ�����
			if(list.size()!=0){
				ExerciseSubject sbj = list.get(0);
				List<Knowledge> list2 = session.createQuery("from Knowledge k where k.label = :label and k.subject = :subject")
						.setString("label", label).setEntity("subject", sbj).list();
				//֪ʶͼ���Ѿ�����
				if(list2.size()!=0){
					universalResult.setResultCode(0);
					return universalResult;
				}
				
				Knowledge knowledge = new Knowledge();
				knowledge.setLabel(label);
				knowledge.setSubject(sbj);
				knowledge.setDescription(description);
				sbj.getKnowledges().add(knowledge);
				int id = (Integer)session.save(knowledge);
				universalResult.setId(id);
				universalResult.setResultCode(1);
			}else{
				ExerciseSubject sbj = new ExerciseSubject(subject, grade);
				Knowledge knowledge = new Knowledge();
				knowledge.setLabel(label);
				knowledge.setSubject(sbj);
				knowledge.setDescription(description);
				Set<Knowledge> set = new HashSet<Knowledge>();
				sbj.setKnowledges(set);
				sbj.getKnowledges().add(knowledge);
				session.save(sbj);
				session.clear();
				int id = (Integer)session.save(knowledge);
				session.clear();
				universalResult.setId(id);
				universalResult.setResultCode(1);
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		return universalResult;
	}
	
	public List<Knowledge> getKnowledges(){
		List<Knowledge> list = new ArrayList<Knowledge>();
		List<Knowledge> list2 = new ArrayList<Knowledge>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			list = session.createQuery("from Knowledge k").list();
			for(Knowledge k:list){
				Knowledge knowledge = new Knowledge();
				ExerciseSubject subject = k.getSubject();
				String grade = String.valueOf(subject.getGrade());
				String sbj = subject.getSubject();
				knowledge.setId(k.getId());
				knowledge.setLabel(k.getLabel()+","+grade+","+sbj);
				knowledge.setDescription(k.getDescription());
				list2.add(knowledge);
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list2;
	}
	
	public Set<Knowledge> getKnowledgeByInfo(int grade, String subject){
		Set<Knowledge> set = new HashSet<Knowledge>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<ExerciseSubject> list = session.createQuery("from ExerciseSubject s where s.grade=:grade and s.subject=:subject")
					.setString("subject", subject).setInteger("grade", grade).list();
			if(list.size()!=0){
				ExerciseSubject subject2 = new ExerciseSubject();
				subject2.setId(list.get(0).getId());
				subject2.setGrade(grade);
				subject2.setSubject(subject);
				subject2.setKnowledges(null);
				subject2.setFillInBlankExercises(null);
				subject2.setMultipleChoicesExercises(null);
				for(Knowledge k:list.get(0).getKnowledges()){
					Knowledge knowledge = new Knowledge();
					knowledge.setId(k.getId());
					knowledge.setLabel(k.getLabel());
					knowledge.setSubject(subject2);
					set.add(knowledge);
				}
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return set;
	}
	
	public UniversalResult deleteKnowledge(int id){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Knowledge knowledge = session.get(Knowledge.class, id);
			session.delete(knowledge);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			universalResult.setResultCode(0);
		} finally{
			transaction.commit();
		}
		return universalResult;
	}
	
	public UniversalResult uploadFillInBlankExercise(Map<String, String> map){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		String subject = map.get("subject");
		int grade = Integer.valueOf(map.get("grade"));
		String knowledge = map.get("knowledge");
		String question = map.get("question");
		String answer = map.get("answer");
		String analysis = map.get("analysis");
		FillInBlankExercise exercise = new FillInBlankExercise();
		exercise.setAnalysis(analysis);
		exercise.setAnswer(answer);
		exercise.setQuestion(question);
		exercise.setCorrectTimes(0);
		exercise.setFinishedTimes(0);
		
		try {
			List<ExerciseSubject> list = session.createQuery("from ExerciseSubject s where s.grade=:grade and s.subject=:subject")
					.setString("subject", subject).setInteger("grade", grade).list();
			List<Knowledge> list2 = session.createQuery("from Knowledge k where k.label=:label")
					.setString("label", knowledge).list();
			
			ExerciseSubject s = list.get(0);
			Knowledge k = list2.get(0);
			Set<Knowledge> set = new HashSet<Knowledge>();
			set.add(k);
			
			exercise.setKnowledges(set);
			exercise.setSubject(s);
			int id = (Integer)session.save(exercise);
			universalResult.setId(id);
			transaction.commit();
			universalResult.setResultCode(1);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		}
		
		return universalResult;
	}

	public UniversalResult uploadMultipleChoicesExercise(Map<String, String> map){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		String subject = map.get("subject");
		int grade = Integer.valueOf(map.get("grade"));
		String knowledge = map.get("knowledge");
		String question = map.get("question");
		String answer1 = map.get("answer1");
		String answer2 = map.get("answer2");
		String answer3 = map.get("answer3");
		String answer4 = map.get("answer4");
		int rightAnswer = Integer.valueOf(map.get("rightAnswer"));
		String analysis = map.get("analysis");
		
		MultipleChoicesExercise exercise = new MultipleChoicesExercise();
		exercise.setAnalysis(analysis);
		exercise.setAnswer(rightAnswer);
		exercise.setOption1(answer1);
		exercise.setOption2(answer2);
		exercise.setOption3(answer3);
		exercise.setOption4(answer4);
		exercise.setQuestion(question);
		exercise.setFinishedTimes(0);
		exercise.setCorrectTimes(0);
		
		try {
			List<ExerciseSubject> list = session.createQuery("from ExerciseSubject s where s.grade=:grade and s.subject=:subject")
					.setString("subject", subject).setInteger("grade", grade).list();
			List<Knowledge> list2 = session.createQuery("from Knowledge k where k.label=:label")
					.setString("label", knowledge).list();
			ExerciseSubject s = list.get(0);
			
			Knowledge k = list2.get(0);
		
			Set<Knowledge> set = new HashSet<Knowledge>();
			set.add(k);
			
			exercise.setKnowledges(set);
			exercise.setSubject(s);
			universalResult.setId((Integer)session.save(exercise));
			transaction.commit();
			universalResult.setResultCode(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return universalResult;
	}

	public Set<FillInBlankExercise> getFillInBlankExercises(){
		List<FillInBlankExercise> list = new ArrayList<FillInBlankExercise>();
		Set<FillInBlankExercise> set = new HashSet<FillInBlankExercise>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			list = session.createQuery("from FillInBlankExercise e").list();
			for(FillInBlankExercise e:list){
				FillInBlankExercise exercise = new FillInBlankExercise();
				exercise.setId(e.getId());
				exercise.setAnalysis(e.getAnalysis());
				exercise.setAnswer(e.getAnswer());
				exercise.setFinishedTimes(e.getFinishedTimes());
				exercise.setCorrectTimes(e.getCorrectTimes());
				exercise.setImageURL(e.getImageURL());
				
				String grade = String.valueOf(e.getSubject().getGrade());
				String subject = e.getSubject().getSubject();
				String knowledge = "";
				for(Knowledge k:e.getKnowledges()){
					knowledge += k.getLabel()+" ";
				}
				
				exercise.setQuestion(e.getQuestion()+","+grade+","+subject+","+knowledge);
				set.add(exercise);
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return set;
	}

	public Set<MultipleChoicesExercise> getMultipleChoicesExercises(){
		List<MultipleChoicesExercise> list = new ArrayList<MultipleChoicesExercise>();
		Set<MultipleChoicesExercise> set = new HashSet<MultipleChoicesExercise>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			list = session.createQuery("from MultipleChoicesExercise e").list();
			for(MultipleChoicesExercise e:list){
				MultipleChoicesExercise exercise = new MultipleChoicesExercise();
				exercise.setAnalysis(e.getAnalysis());
				exercise.setAnswer(e.getAnswer());
				exercise.setId(e.getId());
				exercise.setCorrectTimes(e.getCorrectTimes());
				exercise.setFinishedTimes(e.getFinishedTimes());
				exercise.setOption1(e.getOption1());
				exercise.setOption2(e.getOption2());
				exercise.setOption3(e.getOption3());
				exercise.setOption4(e.getOption4());
				exercise.setImageURL(e.getImageURL());
				
				String grade = String.valueOf(e.getSubject().getGrade());
				String subject = e.getSubject().getSubject();
				String knowledge = "";
				for(Knowledge k:e.getKnowledges()){
					knowledge += k.getLabel()+" ";
				}
				
				exercise.setQuestion(e.getQuestion()+","+grade+","+subject+","+knowledge);
				set.add(exercise);
			}
			transaction.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return set;
	}
	
	public UniversalResult uploadKnowledgeRelation(Map<String, String> map){
		UniversalResult universalResult = new UniversalResult();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		KnowledgeRelationship knowledgeRelationship = new KnowledgeRelationship();
		
		try {
			List<Knowledge> list1 = session.createQuery("from Knowledge k where k.label=:label")
					.setString("label", map.get("kid1")).list();
			List<Knowledge> list2 = session.createQuery("from Knowledge k where k.label=:label")
					.setString("label", map.get("kid2")).list();
			Knowledge k1 = null;
			Knowledge k2 = null;
			if(!list1.isEmpty())
				k1 = list1.get(0);
			if(!list2.isEmpty())
				k2 = list2.get(0);
			String relation = map.get("relation");
			knowledgeRelationship.setKnowledge1(k1);
			knowledgeRelationship.setKnowledge2(k2);
			knowledgeRelationship.setRelationship(relation);
			session.save(knowledgeRelationship);
			universalResult.setResultCode(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			transaction.commit();
		}
		return universalResult;
	}

	
	public List<KnowledgeRelationship> getKnowledgeRelationships() {
		// TODO Auto-generated method stub
		List<KnowledgeRelationship> knowledgeRelationships = new ArrayList<KnowledgeRelationship>();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<KnowledgeRelationship> list = session.createQuery("from KnowledgeRelationship kr").list();
			for(KnowledgeRelationship kr:list){
				KnowledgeRelationship knowledgeRelationship = new KnowledgeRelationship();
				Knowledge knowledge1 = new Knowledge();
				Knowledge knowledge2 = new Knowledge();
				if(kr.getKnowledge1()!=null){
					ExerciseSubject subject = kr.getKnowledge1().getSubject();
					String grade = String.valueOf(subject.getGrade());
					String sbj = subject.getSubject();
					knowledge1.setDescription(kr.getKnowledge1().getDescription());
					knowledge1.setLabel(kr.getKnowledge1().getLabel()+","+grade+","+sbj);
					knowledge1.setId(kr.getKnowledge1().getId());
				}
				if(kr.getKnowledge2()!=null){
					ExerciseSubject subject = kr.getKnowledge2().getSubject();
					String grade = String.valueOf(subject.getGrade());
					String sbj = subject.getSubject();
					knowledge2.setDescription(kr.getKnowledge2().getDescription());
					knowledge2.setLabel(kr.getKnowledge2().getLabel()+","+grade+","+sbj);
					knowledge2.setId(kr.getKnowledge2().getId());
				}
				knowledgeRelationship.setKnowledge1(knowledge1);
				knowledgeRelationship.setKnowledge2(knowledge2);
				knowledgeRelationship.setRelationship(kr.getRelationship());
				knowledgeRelationships.add(knowledgeRelationship);
			}
		} finally {
			// TODO: handle finally clause
		}
		return knowledgeRelationships;
	}

	
}