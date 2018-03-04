package edu.whu.iss.wen.bean;

import java.util.Set;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.bean.Major;
import edu.whu.iss.bean.Rank;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalCourseLearning;

public class Course {
	private int id;

	private String name;
	private int focusNumber;
	private String coverURL;
	private String description;
	private String grade;
	private int chapterNumber;
	private String loadedNumber;
	private float avgRank;
	private Teacher teacher;
	private String subject;
	private String semester;
	private Major major;
	
	private Set<Student> students;
	private Set<Chapter> chapters;
	private Set<Rank> ranks;
	private Set<Course> courses;
	private Set<AdminClass> classes;
	private ChatGroup chatGroup;
    private Set<CourseLearning> learnings;
    private Set<TotalCourseLearning> totalLearnings;
    private Set<ExerciseCatagory> exerciseCatagorys;
	

  
	public Set<ExerciseCatagory> getExerciseCatagorys() {
		return exerciseCatagorys;
	}

	public void setExerciseCatagorys(Set<ExerciseCatagory> exerciseCatagorys) {
		this.exerciseCatagorys = exerciseCatagorys;
	}

	public Course(int id, String name, int focusNumber, String coverURL,
			int chapterNumber) {
		super();
		this.id = id;
		this.name = name;
		this.focusNumber = focusNumber;
		this.coverURL = coverURL;
		this.chapterNumber = chapterNumber;
	}

	public Set<AdminClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<AdminClass> classes) {
		this.classes = classes;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Course(int id, String name,int chapterNumber, String coverURL,
			Teacher teacher) {
		super();
		this.id = id;
		this.name = name;
		this.coverURL = coverURL;
		this.chapterNumber = chapterNumber;
		this.teacher = teacher;
	}

	public Course(int id, String name,String subject,String grade, int focusNumber, int chapterNumber,
			String coverURL) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.grade = grade;
		this.focusNumber = focusNumber;
		this.coverURL = coverURL;
		this.chapterNumber = chapterNumber;
	}

	public Course(int id, String name, String description, Teacher teacher,
			float avgRank, Set<Chapter> chapters,ChatGroup chatGroup) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.avgRank = avgRank;
		this.teacher = teacher;
		this.chapters = chapters;
		this.chatGroup = chatGroup;
	}

	public Course() {
		super();
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public String getLoadedNumber() {
		return loadedNumber;
	}

	public void setLoadedNumber(String loadedNumber) {
		this.loadedNumber = loadedNumber;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFocusNumber() {
		return focusNumber;
	}

	public void setFocusNumber(int focusNumber) {
		this.focusNumber = focusNumber;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getSubject() {
		return subject;
	}

	public Set<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(Set<Rank> ranks) {
		this.ranks = ranks;
	}

	public float getAvgRank() {
		return avgRank;
	}

	public void setAvgRank(float avgRank) {
		this.avgRank = avgRank;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public ChatGroup getChatGroup() {
		return chatGroup;
	}

	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
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

	
	

}
