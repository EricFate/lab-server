package edu.whu.iss.bean;

import edu.whu.iss.wen.bean.Course;

public class TotalCourseLearning {
	private int id;
	private long duration;
	private Course course;
	private Student student;
	public TotalCourseLearning(int id, long duration, Course course) {
		super();
		this.id = id;
		this.duration = duration;
		this.course = course;
	}
	public TotalCourseLearning() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
