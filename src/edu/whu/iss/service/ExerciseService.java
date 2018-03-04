package edu.whu.iss.service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.ExerciseInfo;
import edu.whu.iss.bean.PrivateExCate;
import edu.whu.iss.bean.PrivateExCateDetail;
import edu.whu.iss.bean.PrivateExDetail;
import edu.whu.iss.bean.Student;
import edu.whu.iss.dao.ExerciseDAO;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Exercise;
import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.bean.Lesson;

public class ExerciseService {
	private ExerciseDAO dao = new ExerciseDAO();
	private static ExerciseService instance = new ExerciseService();
	public static ExerciseService getInstance() {
		return instance;
	}
	public List<PrivateExCate> getExerciseCatagories(int id,int cid){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course course = session.get(Course.class, cid);
		List<PrivateExCate> details = new ArrayList<PrivateExCate>();
		Student student = session.get(Student.class, id);
//		List<Integer> ecids = new ArrayList<Integer>();
		Set<ExerciseCatagory> exerciseCatagorys = course.getExerciseCatagorys();
//		for (ExerciseCatagory exerciseCatagory : exerciseCatagorys) {
//			ecids.add(exerciseCatagory.getId());
//		}
		if (exerciseCatagorys.size()==0) {
			return details;
		}
		List<PrivateExCateDetail> categories = dao.getExerciseCategories(student,exerciseCatagorys);
//		List<PrivateExCateDetail> categories = dao.getExerciseCategories(student,ecids);
		for (PrivateExCateDetail privateExCateDetail : categories) {
			PrivateExCate detail = new PrivateExCate();
			ExerciseCatagory catagory = privateExCateDetail.getExerciseCatagory();
			detail.setId(catagory.getId());
			detail.setName(catagory.getName());
			detail.setTotal(catagory.getTotal());
			detail.setCorrect(privateExCateDetail.getCorrect());
			detail.setProgress(privateExCateDetail.getProgress());
			detail.setTotalAnswer(privateExCateDetail.getTotal());
			details.add(detail);
		}
		transaction.commit();
		return details;
	}
	public List<ExerciseInfo> getExercise(int id,int start,String type,int num){
		
		switch (type){
			case "normal":
				return getNormalExercises(id, start, type, num);
			case "daily":
				return getDailyExercises(id, start, type, num);
		}
		return new ArrayList<ExerciseInfo>();
	}
	private List<ExerciseInfo> getNormalExercises(int id,int start,String type,int num) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ExerciseCatagory catagory = session.get(ExerciseCatagory.class, id);
		Set<Exercise> oExercises = catagory.getExercises();
		transaction.commit();
		List<ExerciseInfo> exercises = new ArrayList<ExerciseInfo>();
		int i = 0;
		if(oExercises!=null)
			for (Exercise exercise : oExercises) {
				if (i>=start+num) {
					break;
				}
				if (i>=start) {
					ExerciseInfo info = new ExerciseInfo();
					exercise.setExerciseCatagory(null);
					exercise.setExDetails(null);
					try {
						BeanUtils.copyProperties(info, exercise);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					exercises.add(info);
				}
				i++;
			}
		return exercises;
		
	}
	
	//��ȡ�ճ���ϰ
	private List<ExerciseInfo> getDailyExercises(int id,int start,String type,int num) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		Set<PrivateExDetail> exDetails = student.getExDetails();
		List<PrivateExDetail> exDetailsList = new ArrayList<PrivateExDetail>(exDetails);
		Collections.sort(exDetailsList,new Comparator<PrivateExDetail>() {

			@Override
			public int compare(PrivateExDetail o1, PrivateExDetail o2) {
				// TODO Auto-generated method stub
				Double accuracy1 = (double)o1.getCorrect()/o1.getTotal();
				Double accuracy2 = (double)o2.getCorrect()/o2.getTotal();
				
				return accuracy1.compareTo(accuracy2);
			}
		});
		List<ExerciseInfo> exercises = new ArrayList<ExerciseInfo>();
		int i = 0;
		if(exDetailsList!=null)
			for (PrivateExDetail exercise : exDetailsList) {
				if (i>=start+num) {
					break;
				}
				if (i>=start) {
					ExerciseInfo exerciseInfo = new ExerciseInfo();
					try {
						BeanUtils.copyProperties(exerciseInfo, exercise.getExercise());
						BeanUtils.copyProperties(exerciseInfo, exercise);
						exerciseInfo.setId(exercise.getExercise().getId());
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					exercises.add(exerciseInfo);
				}
				i++;
			}
		transaction.commit();
		return exercises;	
	}
//	private List<ExerciseInfo> getNormalExercises(int id,int start,String type,int num) {
//		Session session = HibernateUtils.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		ExerciseCatagory catagory = session.get(ExerciseCatagory.class, id);
//		Set<Exercise> oExercises = catagory.getExercises();
//		transaction.commit();
//		List<Exercise> exercises = new ArrayList<Exercise>();
//		int i = 0;
//		if(oExercises!=null)
//			for (Exercise exercise : oExercises) {
//				if (i>=start+num) {
//					break;
//				}
//				if (i>=start) {
//					exercise.setExerciseCatagory(null);
//					exercise.setExDetails(null);
//					exercises.add(exercise);
//				}
//				i++;
//			}
//		return exercises;
//		
//	}
	
	
	
	public Set<Exercise> getLessonExercises(int lid){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Lesson lesson = session.get(Lesson.class, lid);
		ExerciseCatagory exerciseCatagory = lesson.getExerciseCatagory();
		Set<Exercise> exercises ;
		if (exerciseCatagory!=null) {
			exercises = exerciseCatagory.getExercises();
		}else {
			exercises = new HashSet<Exercise>();
		}
		transaction.commit();
		for (Exercise exercise : exercises) {
			exercise.setExerciseCatagory(null);
			exercise.setExDetails(null);
		}
		return exercises;
	}
	public void saveResult(int id,Map<Integer, Integer> content,String ecid){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		Set<PrivateExDetail> details = student.getExDetails();
		if (details==null) {
			details = new HashSet<PrivateExDetail>();
			student.setExDetails(details);
		}
		Set<PrivateExDetail> exDetails = student.getExDetails();
		Set<Entry<Integer,Integer>> entrySet = content.entrySet();
	    outer:for (Entry<Integer, Integer> entry : entrySet) {
			for (PrivateExDetail exdetail : exDetails) {
				if (exdetail.getExercise()!=null&&exdetail.getExercise().getId()==entry.getKey()) {
					exdetail.setTotal(exdetail.getTotal()+1);
					exdetail.setCorrect(exdetail.getCorrect()+entry.getValue());
					continue outer;
				}
			}
			PrivateExDetail detail = new PrivateExDetail();
			detail.setExercise(session.get(Exercise.class, entry.getKey()));
			detail.setTotal(1);
			detail.setCorrect(entry.getValue());
			detail.setStudent(student);
			exDetails.add(detail);
		}
		if (ecid!=null) {
			int correctAnswer =0;
			for (Entry<Integer, Integer> entry : entrySet) {
				correctAnswer+=entry.getValue();
			}
			List<ExerciseCatagory> list = new ArrayList<ExerciseCatagory>();
			int iecid = Integer.parseInt( ecid);
			list.add(session.get(ExerciseCatagory.class, iecid));
			PrivateExCateDetail detail = dao.getExerciseCategories(student, list).get(0);
			detail.setProgress(dao.getFinishedCount(id,iecid));
			detail.setCorrect(detail.getCorrect()+correctAnswer);
			detail.setTotal(detail.getTotal()+entrySet.size());
		}
		transaction.commit();
	}
}
