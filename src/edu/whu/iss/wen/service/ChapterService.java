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

	// 获取所有章节信息
	public Set<Chapter> getAllChapterInfoInService(int id) {
		Set<Chapter> chapters = chapterDAO.getAllChapterInfoInDAO(id);
		// 防止死循环
		for (Chapter chapter : chapters) {
			chapter.setCourse(null);
			chapter.setLessons(null);
		}
		return chapters;
	}
	

	// 获取单个章节信息
	public Chapter getSingleChapterInfoInService(int id) {
		Chapter chapter = chapterDAO.getSingleChapterInfoInDAO(id);
		// 防止死循环
		chapter.setCourse(null);
		chapter.setLessons(null);
		return chapter;
	}
	
	//修改章节信息
	public UniversalResult chapterInfoReviseInService(Map<String, String[]> map,int id){
		UniversalResult universalResult=chapterDAO.chapterInfoReviseInDAO(map, id);
		return universalResult; 
	}
	
	//章节课时数加一
	public UniversalResult lessonNumberPlusInService(int id){
		UniversalResult result=chapterDAO.lessonNumberPlusInDAO(id);
		return result;
	}
	
	//删除课时
	public UniversalResult deleteChapterInService(int id){
		return chapterDAO.deleteChapterInDAO(id);
	}
}
