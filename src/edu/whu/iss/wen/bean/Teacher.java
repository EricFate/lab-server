package edu.whu.iss.wen.bean;

import java.util.Set;

public class Teacher {
	
	private  int id;

    private String identity;
    private String username;
    private String password;
    private String realname;
    private String gender;
    private String region;
    private String rank;
    private String subject;
    private String phone;
    private String email;
    private String signature;
    private String imageURL;
    private String token;
    private String school;
    private Set<Course> courses;
    private Set<AdminClass> classes;
    
    
	public Teacher(int id, String realname, String imageURL) {
		super();
		this.id = id;
		this.realname = realname;
		this.imageURL = imageURL;
	}
	public Teacher(int id,String realname){
		this.id=id;
		this.realname=realname;
	}
	
	public Teacher(String realname) {
		super();
		this.realname = realname;
	}

	public Teacher() {
		super();
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signiture) {
        this.signature = signiture;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setClasses(Set<AdminClass> classes){
		this.classes = classes;
	}
	
	public Set<AdminClass> getClasses(){
		return classes;
	}
	
	public void setSchool(String school){
		this.school = school;
	}
	
	public String getSchool(){
		return school;
	}

}
