package edu.whu.iss.bean;

import java.util.Date;
import edu.whu.iss.wen.bean.Course;

//The course of each day for one student.
public class CourseLearning {
	private int id;
	private long duration;
	private Date time;
	private Course course;
	private Student student;
	
	
	public int getId(){
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
