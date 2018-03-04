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
	// 获取所有课时信息
	public Set<Lesson> getLessonInfoInService(int id) {
		Set<Lesson> lessons = lessonDAO.getLessonInfoInDAO(id);
		// 防止死循环
		for (Lesson lesson : lessons) {
			lesson.setChapter(null);
			lesson.setExerciseCatagory(null);
		}
		return lessons;
	}

	// 获取单个课时信息
	public Lesson getSingleLessonInfoInService(int id) {
		Lesson lesson = lessonDAO.getSingleLessonInfoInDAO(id);
		// 防止死循环
		lesson.setChapter(null);
		lesson.setExerciseCatagory(null);
		return lesson;
	}
	
	//修改章节信息
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
	
	//删除课时
	public UniversalResult deleteLessonInService(int id){
		return lessonDAO.deleteLessonInDAO(id);
	}
}
