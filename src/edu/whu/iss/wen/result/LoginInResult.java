package edu.whu.iss.wen.result;

import edu.whu.iss.wen.bean.Teacher;

public class LoginInResult {
	private int resultCode;
	private Teacher teacher;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getClassNum(){
		return teacher.getClasses().size();
	}
	
	@Override
	public String toString() {
		return "LoginInResult [resultCode=" + resultCode + ", teacher="
				+ teacher  +"]";
	}
	
}
