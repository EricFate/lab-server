package edu.whu.iss.bean;

import java.util.Set;

import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;

/**
 * Created by fate on 2016/12/8.
 */

public class ChatGroup {
	private int id;
    private String name;
    private String imageURL;
    private Course course;
    private AdminClass adminClass;
    private Set<UserGroup> userGroups;
    private Set<Notice> notices;
    
    public ChatGroup() {
		super();
	}
    
    
	public ChatGroup(String name, AdminClass adminClass) {
		super();
		this.name = name;
		this.adminClass = adminClass;
	}


	public Set<Notice> getNotices() {
		return notices;
	}


	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}


	public AdminClass getAdminClass() {
		return adminClass;
	}

	public void setAdminClass(AdminClass adminClass) {
		this.adminClass = adminClass;
	}

	public ChatGroup(String name, Course course) {
		super();
		this.name = name;
		this.course = course;
	}
	public ChatGroup(int id, String name, String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
    
}
