package edu.whu.iss.wen.bean;

import java.util.Set;

import edu.whu.iss.bean.AddClassRequest;
import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.CollegeStudent;

public class AdminClass {
	private int id;
	private int sNumber;
	private int csNumber;
	private String region;
	private String school;
	private String grade;
	private String imageURL;
	private Teacher teacher;
	private Set<Student> students;
	private Set<CollegeStudent> collegeStudents;
	private Set<Course> courses;
	private Set<Notice> notices;
	private ChatGroup chatGroup;
	private Set<AddClassRequest> requests;
	
	public Set<Notice> getNotices() {
		return notices;
	}
	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		

	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getsNumber() {
		return sNumber;
	}
	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<CollegeStudent> getCollegeStudents() {
		return collegeStudents;
	}
	public void setCollegeStudents(Set<CollegeStudent> collegeStudents) {
		this.collegeStudents = collegeStudents;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCsNumber() {
		return csNumber;
	}
	public void setCsNumber(int csNumber) {
		this.csNumber = csNumber;
	}
	public ChatGroup getChatGroup() {
		return chatGroup;
	}
	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Set<AddClassRequest> getRequests() {
		return requests;
	}
	public void setRequests(Set<AddClassRequest> requests) {
		this.requests = requests;
	}

}
