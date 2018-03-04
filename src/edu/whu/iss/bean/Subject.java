package edu.whu.iss.bean;

import java.util.HashSet;
import java.util.Set;

import edu.whu.iss.wen.bean.Course;

/**
 * Created by fate on 2016/12/10.
 */

public class Subject {
	private int id;
    private String title;
    private Set<Course> courses = new HashSet<Course>();
    
    public Subject() {
		super();
	}

	public Subject(String title, Set<Course> subjects) {
        this.title = title;
        this.setCourses(subjects);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



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
}
