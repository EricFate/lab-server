package edu.whu.iss.wen.service;

import java.util.Map;
import java.util.Set;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.dao.ChapterDAO;
import edu.whu.iss.wen.result.UniversalResult;


public class ChapterService {
	ChapterDAO chapterDAO = new ChapterDAO();

	public UniversalResult chapterUploadInService(Chapter chapter) {
		return chapterDAO.chapterUploadInDAO(chapter);
	}

	// ��ȡ�����½���Ϣ
	public Set<Chapter> getAllChapterInfoInService(int id) {
		Set<Chapter> chapters = chapterDAO.getAllChapterInfoInDAO(id);
		// ��ֹ��ѭ��
		for (Chapter chapter : chapters) {
			chapter.setCourse(null);
			chapter.setLessons(null);
		}
		return chapters;
	}
	

	// ��ȡ�����½���Ϣ
	public Chapter getSingleChapterInfoInService(int id) {
		Chapter chapter = chapterDAO.getSingleChapterInfoInDAO(id);
		// ��ֹ��ѭ��
		chapter.setCourse(null);
		chapter.setLessons(null);
		return chapter;
	}
	
	//�޸��½���Ϣ
	public UniversalResult chapterInfoReviseInService(Map<String, String[]> map,int id){
		UniversalResult universalResult=chapterDAO.chapterInfoReviseInDAO(map, id);
		return universalResult; 
	}
	
	//�½ڿ�ʱ����һ
	public UniversalResult lessonNumberPlusInService(int id){
		UniversalResult result=chapterDAO.lessonNumberPlusInDAO(id);
		return result;
	}
	
	//ɾ����ʱ
	public UniversalResult deleteChapterInService(int id){
		return chapterDAO.deleteChapterInDAO(id);
	}
}
