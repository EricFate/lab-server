package edu.whu.iss.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.AddClassRequest;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Major;
import edu.whu.iss.bean.PrivateExCateDetail;
import edu.whu.iss.bean.Rank;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalCourseLearning;
import edu.whu.iss.bean.UserGroup;
import edu.whu.iss.dao.SubjectDAO;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.bean.Teacher;

public class SubjectService {
	private SubjectDAO subjectDAO = SubjectDAO.getInstance();
	private static SubjectService instance = new SubjectService();

	public static SubjectService getInstance() {
		return instance;
	}

	public void saveSubject(Course subject) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		subjectDAO.saveSubject(subject);
		transaction.commit();
	}

	// public Subject getSubjectDetail(int sid){
	//
	// Session session = HibernateUtils.getCurrentSession();
	// Transaction transaction = session.beginTransaction();
	// Subject subject = subjectDAO.getSubject(sid);
	// transaction.commit();
	// subject.setNumber(null);
	// subject.setDuration(null);
	// subject.setRanks(null);
	// Student teacher = subject.getTeacher();
	// teacher =new Student(teacher.getId(), teacher.getRealname(),
	// teacher.getImageURL());
	// subject.setTeacher(teacher);
	// return subject;
	// }
	public List<Major> getAllSubjects() {

		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Major> categories = subjectDAO.getAllSubject();
		transaction.commit();
		for (Major category : categories) {
			Set<Course> newSubjects = new HashSet<Course>();
			Set<Course> subjects = category.getCourses();
			Iterator<Course> iterator = subjects.iterator();
			while (iterator.hasNext()) {
				Course s = iterator.next();
				newSubjects.add(new Course(s.getId(), s.getName(), s
						.getSubject(), s.getGrade(), s.getFocusNumber(), s
						.getChapterNumber(), s.getCoverURL()));
			}
			category.setCourses(newSubjects);
		}
		return categories;
	}

	public List<TotalCourseLearning> getMySubjects(int id, int start) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// List<Course> subjects = subjectDAO.getMySubject(id, start);
//		Student s = (Student) session.get(Student.class, id);
		List<TotalCourseLearning> learnings = subjectDAO.getMySubject(id, start);
		
		for (TotalCourseLearning learning : learnings) {
			Course c = learning.getCourse();
			Teacher t = c.getTeacher();
			c = new Course(c.getId(), c.getName(), c.getChapterNumber(), c.getCoverURL(), new Teacher(t.getRealname()));
			learning.setCourse(c);
		}
		session.clear();
		transaction.commit();
		return learnings;
	}

	public Set<Rank> getRanks(int sid) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course subject = subjectDAO.getSubject(sid);
		transaction.commit();
		Set<Rank> ranks = subject.getRanks();
		for (Rank rank : ranks) {
			Student ranker = rank.getRanker();
			ranker = new Student(ranker.getId(), ranker.getNickname(),
					ranker.getImageURL());
			rank.setRanker(ranker);
		}
		return ranks;
	}

	// public Set<Subject> getMySubjects(int uid,int start) {
	// Session session = HibernateUtils.getCurrentSession();
	// Transaction transaction = session.beginTransaction();
	// Set<Subject> allSubject = subjectDAO.getMySubject(uid,start);
	// transaction.commit();
	// for (Subject subject : allSubject) {
	// Student teacher = subject.getTeacher();
	// teacher =new Student(teacher.getId(), teacher.getRealname(),
	// teacher.getImageURL());
	// subject.setTeacher(teacher);
	// }
	// return allSubject;
	// }
	public Course loadCourseDetail(int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course c = subjectDAO.getSubject(cid);
		c = new Course(c.getId(), c.getName(), c.getDescription(),
				c.getTeacher(), c.getAvgRank(), c.getChapters(),c.getChatGroup());
		Teacher t = c.getTeacher();
		c.setTeacher(new Teacher(t.getId(), t.getRealname(), t.getImageURL()));
		Set<Chapter> ch = c.getChapters();
		Set<Chapter> newChs = new HashSet<Chapter>();
		for (Chapter chapter : ch) {
			Chapter newC = new Chapter();
			newC.setChapterName(chapter.getChapterName());
			Set<Lesson> lessons = chapter.getLessons();
			Set<Lesson> newLs = new HashSet<Lesson>();
			for (Lesson lesson : lessons) {
				Lesson newL = new Lesson();
				newL.setLessonName(lesson.getLessonName());
				newL.setId(lesson.getId());
				newLs.add(newL);
			}
			newC.setLessons(newLs);
			newC.setId(chapter.getId());
			newChs.add(newC);
		}
		c.setChapters(newChs);
		c.getChatGroup().setCourse(null);
		c.getChatGroup().setUserGroups(null);
		c.getChatGroup().setAdminClass(null);
		c.getChatGroup().setNotices(null);
		session.clear();
		transaction.commit();
		return c;
	}

	public void startLearning(int id, int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, id);
		Course c = session.get(Course.class, cid);
		s.getCourses().add(c);
		String gid ="g" + c.getChatGroup().getId();
		c.setFocusNumber(c.getFocusNumber() + 1);
		TotalCourseLearning total = new TotalCourseLearning();
		total.setStudent(s);
		total.setCourse(c);
		session.save(total);
		System.out.println("cid:" + cid);
		try {
			if(!gid.equals("g0")){
			RongCloudUtils.getInstance().group.join(new String[] { "s" + id },
					gid , c.getName());
			}
			UserGroup userGroup = new UserGroup();
			userGroup.setChatGroup(c.getChatGroup()); 
			userGroup.setUid("s"+id);
			session.save(userGroup);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<ExerciseCatagory> catagorys = c.getExerciseCatagorys();
		Set<PrivateExCateDetail> details = new HashSet<PrivateExCateDetail>();
		for (ExerciseCatagory exerciseCatagory : catagorys) {
			PrivateExCateDetail e = new PrivateExCateDetail();
			e.setStudent(s);
			e.setExerciseCatagory(exerciseCatagory);
			details.add(e);
		}
		Set<PrivateExCateDetail> exCateDetails = s.getExCateDetails();
		if (exCateDetails==null) {
			s.setExCateDetails(details);
		}else {
			exCateDetails.addAll(details);
		}
		session.save(s);
		transaction.commit();
	}

	public boolean checkLearning(int id, int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, id);
		Set<Course> courses = s.getCourses();
		transaction.commit();
		return checkLearning(courses, cid);
	}

	private boolean checkLearning(Set<Course> courses, int cid) {
		// TODO Auto-generated method stub
		for (Course course : courses) {
			if (course.getId() == cid)
				return true;
		}
		return false;
	}

	public void submitRank(int id, String content, float rank, int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Rank r = new Rank(content, rank);
		Student s = session.get(Student.class, id);
		Course c = session.get(Course.class, cid);
		r.setRanker(s);
		r.setCourse(c);
		r.setTime(new Date());
		float avg = r.getRank();
		Set<Rank> ranks = c.getRanks();
		for (Rank rk : ranks) {
			avg += rk.getRank();
		}
		avg /= ranks.size() + 1;
		c.setAvgRank(avg);
		session.save(r);

		transaction.commit();
	}

	public Set<Rank> loadRanks(int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course c = session.get(Course.class, cid);
		Set<Rank> ranks = c.getRanks();
		System.out.println("+++++++++++" + ranks);
		transaction.commit();
		for (Rank rank : ranks) {
			rank.setCourse(null);
			Student ranker = rank.getRanker();
			ranker = new Student(ranker.getNickname(), ranker.getImageURL());
			rank.setRanker(ranker);
		}
		return ranks;

	}

	public Lesson loadLessonDetail(int lid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Lesson lesson = session.get(Lesson.class, lid);
		transaction.commit();
		lesson.setChapter(null);
		lesson.setVideoURL(null);
		lesson.setExerciseCatagory(null);
		return lesson;
	}

	public List<Chapter> loadChapters(int cid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course c = session.get(Course.class, cid);
		transaction.commit();
		Set<Chapter> ch = c.getChapters();
		List<Chapter> newChs = new ArrayList<Chapter>();
		for (Chapter chapter : ch) {
			Chapter newC = new Chapter();
			newC.setChapterName(chapter.getChapterName());
			Set<Lesson> lessons = chapter.getLessons();
//			Set<Lesson> newLs = new HashSet<Lesson>();
//			for (Lesson lesson : lessons) {
//				Lesson newL = new Lesson();
//				newL.setId(lesson.getId());
//				newL.setLessonName(lesson.getLessonName());
//				newL.setVideoURL(lesson.getVideoURL());
//				newLs.add(newL);
//			}
			for (Lesson lesson : lessons) {
				lesson.setExerciseCatagory(null);
				lesson.setChapter(null);
			}
			newC.setLessons(lessons);
			newChs.add(newC);
		}
		return newChs;
	}

	public Set<Rank> loadMyRanks(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, id);
		Set<Rank> ranks = s.getRanks();
		System.out.println(ranks);
		transaction.commit();
		for (Rank rank : ranks) {
			rank.setRanker(null);
			Course course = rank.getCourse();
			rank.setCourse(new Course(course.getId(), course.getName()));
		}
		return ranks;
	}

	public void deleteRank(int rid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Rank r = session.get(Rank.class, rid);
		session.delete(r);
		transaction.commit();
	}

	public void courseLearning(int sid, int cid, long duration) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CourseLearning learning = subjectDAO.getCourseLearningByInfo(sid, cid);
		Student s = session.get(Student.class, sid);
		Course c = session.get(Course.class, cid);
		if (learning == null) {
			learning = new CourseLearning();
			learning.setStudent(s);
			learning.setCourse(c);
			learning.setTime(new Date());
		}
		learning.setDuration(learning.getDuration() + duration);
		session.save(learning);
		TotalCourseLearning totalLearning = subjectDAO
				.getTotalCourseLearningByInfo(sid, cid);
		if (totalLearning == null) {
			totalLearning = new TotalCourseLearning();
			totalLearning.setStudent(s);
			totalLearning.setCourse(c);
		}
		totalLearning.setDuration(totalLearning.getDuration() + duration);
		session.save(totalLearning);
		transaction.commit();

	}
	public void joinClass(int id,int clzId){
		Session session = HibernateUtils.getCurrentSession();
		if (session.getTransaction()!=null&&session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		AdminClass adminClass = session.get(AdminClass.class, clzId);
		AddClassRequest request = new AddClassRequest();
		request.setStudent(student);
		request.setAdminClass(adminClass);
		session.save(request);
		transaction.commit();
	}
	public AdminClass getAdminClass(int acid){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AdminClass adminClass = session.get(AdminClass.class, acid);
		transaction.commit();
		if (adminClass==null) {
			return null;
		}
		adminClass.setChatGroup(null);
		adminClass.setCollegeStudents(null);
		adminClass.setCourses(null);
		adminClass.setNotices(null);
		adminClass.setRequests(null);
		adminClass.setStudents(null);
		adminClass.setTeacher(null);
		return adminClass;
	}

}
