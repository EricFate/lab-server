package edu.whu.iss.bean;

import java.util.Set;

import com.google.gson.annotations.Expose;

import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Knowledge;

public class Student {


	private int id;

	private String identity;
	private String username;
    private String password;
    private String nickname;
    private String email;
    private String number;
    private String realname;
    private String classname;
    private String region;
    private String school;
    private String phone;
    private String signature;
    private String imageURL;
    private String gender;
    private String token;
    private String grade;
    private Set<Course> courses;
    private Set<Issue> issues;
    private Set<Rank> ranks;
    private CollegeStudent collegeStudent;
    private Set<Knowledge> weaknessKnowledges;  //记录该学生薄弱的知识点
    private Parent parent;
    
    public Parent getParent() {
		return parent;
	}

	public Set<Knowledge> getWeaknessKnowledges() {
		return weaknessKnowledges;
	}

	public void setWeaknessKnowledges(Set<Knowledge> weaknessKnowledges) {
		this.weaknessKnowledges = weaknessKnowledges;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	private AdminClass adminClass;
    private Set<CourseLearning> learnings;
    private Set<TotalMessageRecord> totalMessages;
	private Set<TotalCourseLearning> totalLearnings;
    private Set<PrivateExCateDetail> exCateDetails;
	private Set<PrivateExDetail> exDetails;
    public Set<PrivateExCateDetail> getExCateDetails() {
		return exCateDetails;
	}

	public void setExCateDetails(Set<PrivateExCateDetail> exCateDetails) {
		this.exCateDetails = exCateDetails;
	}

	public Set<TotalMessageRecord> getTotalMessages() {
		return totalMessages;
	}

	public void setTotalMessages(Set<TotalMessageRecord> totalMessages) {
		this.totalMessages = totalMessages;
	}

	public AdminClass getAdminClass() {
		return adminClass;
	}



	public Student(int id,String realname, String signature, String imageURL) {
		super();
		this.id=id;
		this.realname = realname;
		this.signature = signature;
		this.imageURL = imageURL;
	}



	public void setAdminClass(AdminClass adminClass) {
		this.adminClass = adminClass;
	}



	public Student(int id, String nickname, String imageURL) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.imageURL = imageURL;
	}



	public Student(String nickname, String imageURL) {
		super();
		this.nickname = nickname;
		this.imageURL = imageURL;
	}



	public Student(int id, String nickname) {
		super();
		this.id = id;
		this.nickname = nickname;
	}



	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}



	public Student(String nickname, String username, String imageURL) {
        this.nickname = nickname;
        this.username = username;
        this.imageURL = imageURL;
    }

    public Student(String username, String password, String nickname, String signiture,String imageURL) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.setSignature(signiture);
        this.imageURL = imageURL;
    }




    public Student() {
		// TODO Auto-generated constructor stub
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }



    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Set<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(Set<Rank> ranks) {
		this.ranks = ranks;
	}

	public CollegeStudent getCollegeStudent() {
		return collegeStudent;
	}

	public void setCollegeStudent(CollegeStudent collegeStudent) {
		this.collegeStudent = collegeStudent;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public Set<CourseLearning> getLearnings() {
		return learnings;
	}



	public void setLearnings(Set<CourseLearning> learnings) {
		this.learnings = learnings;
	}



	public Set<TotalCourseLearning> getTotalLearnings() {
		return totalLearnings;
	}



	public void setTotalLearnings(Set<TotalCourseLearning> totalLearnings) {
		this.totalLearnings = totalLearnings;
	}

	public Set<PrivateExDetail> getExDetails() {
		return exDetails;
	}

	public void setExDetails(Set<PrivateExDetail> exDetails) {
		this.exDetails = exDetails;
	}






}