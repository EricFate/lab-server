package edu.whu.iss.wen.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Exercise;
import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.bean.FillInBlankExercise;
import edu.whu.iss.wen.bean.Knowledge;
import edu.whu.iss.wen.bean.KnowledgeRelationship;
import edu.whu.iss.wen.bean.MultipleChoicesExercise;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.dao.TeacherDAO;
import edu.whu.iss.wen.result.LoginInResult;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.result.UniversalResult;


public class TeacherService {
	TeacherDAO teacherDAO = new TeacherDAO();
	//注册
	public ForIdResult registerTeacherInService(Teacher teacher){
		
		return teacherDAO.registerTeacherInDAO(teacher);
	}
	//判断用户名是否唯一
	public UniversalResult isUsernameUniqueInService(String username){
		return teacherDAO.isUsernameUniqueInDAO(username);
	}
	/*
	//班主任创建班级
	public UniversalResult createClassInService(String token, String grade, String classNum){
		UniversalResult result = new UniversalResult();
		List<Teacher> list = teacherDAO.getTeacherByToken(token);
		if(list.size()==1){
			try{
				Teacher teacher = list.get(0);
				AdminClass adminClass = new AdminClass();
				adminClass.setGrade(grade);
				adminClass.setCsNumber(Integer.valueOf(classNum));
				adminClass.setRegion(teacher.getRegion());
				teacher.getClasses().add(adminClass);
				result.setResultCode(0);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.setResultCode(-1);
			}
		}else{
			result.setResultCode(1);
		}
		return result;
	}
	*/
	
	public LoginInResult loginInTeacherInService(String username,String password){
		List<Teacher> list=teacherDAO.loginInTeacherInDAO(username, password);
		LoginInResult loginInResult=new LoginInResult();
		//账户不存在
		if(list.isEmpty()){
			loginInResult.setResultCode(0);
		}
		//多个账户，为了避免添上的
		else if(list.size()>1){
			loginInResult.setResultCode(1);
		}
		//账户存在且只有一个
		else{
			Teacher teacher=list.get(0);
			//验证密码
			if(!password.equals(teacher.getPassword())){
				loginInResult.setResultCode(2);
			}
			else{
				loginInResult.setResultCode(3);
				loginInResult.setTeacher(teacher);
				for(AdminClass ac:teacher.getClasses()){
					System.out.println("Request size:"+ac.getRequests().size());
				}
			}			
		}
		return loginInResult;
	}
	
	//上传头像
	public UniversalResult imageUploadInService(String id,String imageURL){
		UniversalResult universalResult=teacherDAO.imageUploadInDAO(id, imageURL);
		return universalResult;
	}
	
	//上传填空题图片
	public UniversalResult fillInBlankExerciseImageUpload(String id, String imageURL){
		return teacherDAO.fillInBlankExerciseImageUpload(id, imageURL);
	}
	
	//上传选择题图片
	public UniversalResult multipleChoicesExerciseImageUpload(String id, String imageURL){
		return teacherDAO.multipleChoicesExerciseImageUpload(id, imageURL);
	}
	
	//修改个人信息
	public UniversalResult teacherInfoReviseInService(Map<String, String[]> map,int id){
		UniversalResult universalResult=teacherDAO.teacherInfoReviseInDAO(map, id);
		return universalResult; 
	}
	
	//修改密码
	public UniversalResult passwordReviseInService(String uid,String password){
		return teacherDAO.passwordReviseInDAO(uid, password);
	}
	
	//获取题目
	public List<Exercise> getTestInSetvice(String tid){
		List<Exercise> set=teacherDAO.getTestInDAO(tid);
		for(Exercise cq:set){
			cq.setExerciseCatagory(null);
			cq.setExDetails(null);
		}
		return set;
	}
	
	//获取单个题目
	public Exercise getSingleExerciseInDAO(int id){
		Exercise exercise=teacherDAO.getSingleExerciseInDAO(id);
		exercise.setExerciseCatagory(null);
		exercise.setExDetails(null);
		return exercise;
	}
	
	//上传题目
	public UniversalResult uploadTestInService(ExerciseCatagory ec,int id,int testType){
		return teacherDAO.uploadTestInDAO(ec,id,testType);
	}
	
	//获取题集
	public ExerciseCatagory getExerciseCatagoryInService(char t,int id){
		ExerciseCatagory ec=teacherDAO.getExerciseCatagoryInDAO(t,id);
		if(ec!=null){
			ec.setExercises(null);
			ec.setCourse(null);
			ec.setLesson(null);
			ec.setExCateDetails(null);
		}
		return ec;
	}
	
	public Set<Exercise> getExerciseInService(int id){
		Set<Exercise> set=teacherDAO.getExerciseInDAO(id);
		for(Exercise exercise:set){
			exercise.setExDetails(null);
			exercise.setExerciseCatagory(null);
		}
		return set;
	}
	
	//忘记密码时进行信息验证
	public UniversalResult verifyInfo(int flag, String username, String vrfInfo){
		return teacherDAO.verifyInfo(flag, username, vrfInfo);
	}
	
	//通过认证后重新设定密码
	public UniversalResult setNewPassword(String username, String password){
		return teacherDAO.setNewPassword(username, password);
	}
	
	//上传知识图谱
	public UniversalResult uploadKnowledge(String subject, String grade, String label, String description){
		int gradeInt = 1;
		switch(grade){
		case "一年级":
			gradeInt = 1;
			break;
		case "二年级":
			gradeInt = 2;
			break;
		case "三年级":
			gradeInt = 3;
			break;
		case "四年级":
			gradeInt = 4;
			break;
		case "五年级":
			gradeInt = 5;
			break;
		case "六年级":
			gradeInt = 6;
			break;
		case "七年级":
			gradeInt = 7;
			break;
		case "八年级":
			gradeInt = 8;
			break;
		case "九年级":
			gradeInt = 9;
			break;
		}
		return teacherDAO.uploadKnowledge(subject, gradeInt, label, description);
	}
	
	//获取所有知识标签
	public List<Knowledge> getKnowledges(){
		return teacherDAO.getKnowledges();
	}
	
	//删除单个知识标签
	public UniversalResult deleteKnowledge(int id){
		return teacherDAO.deleteKnowledge(id);
	}
	
	//获取某个学科某个年级的知识标签
	public Set<Knowledge> getKnowledgeByInfo(int grade, String subject){
		return teacherDAO.getKnowledgeByInfo(grade, subject);
	}
	
	//上传填空题
	public UniversalResult uploadFillInBlankExercise(Map<String, String> map){
		return teacherDAO.uploadFillInBlankExercise(map);
	}
	
	//上传选择题
	public UniversalResult uploadMultipleChoicesExercise(Map<String, String> map){
		return teacherDAO.uploadMultipleChoicesExercise(map);
	}
	
	//获取填空题
	public Set<FillInBlankExercise> getFillInBlankExercises(){
		return teacherDAO.getFillInBlankExercises();
	}
	
	//获取选择题
	public Set<MultipleChoicesExercise> getMultipleChoicesExercises(){
		return teacherDAO.getMultipleChoicesExercises();
	}
	
	/*
	//删除题目
	public UniversalResult deleteExercise(int id, int type){
		return teacherDAO.deleteExercise(id, type);
	}
	*/
	
	//上传知识关系
	public UniversalResult uploadKnowledgeRelation(Map<String, String> map){
		return teacherDAO.uploadKnowledgeRelation(map);
	}
	
	
	
	//获取知识点关联关系
	public List<KnowledgeRelationship> getKnowledgeRelationships(){
		return teacherDAO.getKnowledgeRelationships();
	}
	
}
