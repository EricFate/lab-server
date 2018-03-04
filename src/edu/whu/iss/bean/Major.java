package edu.whu.iss.bean;

import java.util.Set;

import edu.whu.iss.wen.bean.Course;

public class Major {
	private int id;
	private String title;
	private Set<Course> courses;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
