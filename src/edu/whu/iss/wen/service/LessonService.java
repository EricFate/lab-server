package edu.whu.iss.wen.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.dao.LessonDAO;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.GetVideoResult;
import edu.whu.iss.wen.result.UniversalResult;


public class LessonService {
	LessonDAO lessonDAO = new LessonDAO();

	public ForIdResult lessonUploadInService(Lesson lesson) {
		return lessonDAO.lessonUploadInDAO(lesson);
	}
	
	
	
	public List<Set<Lesson>> getAllLessonInfoInService(List<Integer> list) {
		List<Set<Lesson>> lll=lessonDAO.getAllLessonInfoInDAO(list);
		for(Set<Lesson> ll:lll){
			for(Lesson l:ll){
				l.setChapter(null);
				l.setDescription(null);
				l.setKnowledgePoint(null);
				l.setVideoImageURL(null);
				l.setVideoURL(null);
				l.setExerciseCatagory(null);
			}
		}
		return lll;
	}
	// ��ȡ���п�ʱ��Ϣ
	public Set<Lesson> getLessonInfoInService(int id) {
		Set<Lesson> lessons = lessonDAO.getLessonInfoInDAO(id);
		// ��ֹ��ѭ��
		for (Lesson lesson : lessons) {
			lesson.setChapter(null);
			lesson.setExerciseCatagory(null);
		}
		return lessons;
	}

	// ��ȡ������ʱ��Ϣ
	public Lesson getSingleLessonInfoInService(int id) {
		Lesson lesson = lessonDAO.getSingleLessonInfoInDAO(id);
		// ��ֹ��ѭ��
		lesson.setChapter(null);
		lesson.setExerciseCatagory(null);
		return lesson;
	}
	
	//�޸��½���Ϣ
	public UniversalResult lessonInfoReviseInService(Map<String, String[]> map,int id){
		UniversalResult universalResult=lessonDAO.lessonInfoReviseInDAO(map, id);
		return universalResult; 
	}
	
	public GetVideoResult getLessonVideoInService(int id){
		return lessonDAO.getLessonVideoInDAO(id);
	}
	
	public UniversalResult videoImageUploadInService(int id,String videoImageURL){
		return lessonDAO.videoImageUploadInDAO(id, videoImageURL);
	}
	
	//ɾ����ʱ
	public UniversalResult deleteLessonInService(int id){
		return lessonDAO.deleteLessonInDAO(id);
	}
}
