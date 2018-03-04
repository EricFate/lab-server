package edu.whu.iss.sd.bean;

import java.util.HashSet;
import java.util.Set;

import edu.whu.iss.bean.Student;
import edu.whu.iss.wen.bean.AdminClass;


public class CollegeStudent {
	
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
	private String major;
	private String introduction;
	private String token;
	private Set<Video> videos = new HashSet<Video>();
	private Set<Answer> answers = new HashSet<Answer>();
	private Set<Question> questions = new HashSet<Question>();
	private Set<Student> students;
	private Set<AdminClass> adminClass;
	/**
	 * @return the questions
	 */
	
	public Set<Question> getQuestions() {
		return questions;
	}
	public Set<AdminClass> getAdminClass() {
		return adminClass;
	}
	public void setAdminClass(Set<AdminClass> adminClass) {
		this.adminClass = adminClass;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the videos
	 */
	public Set<Video> getVideos() {
		return videos;
	}
	/**
	 * @return the answers
	 */
	public Set<Answer> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	/**
	 * @param videos the videos to set
	 */
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String(id+" "+username+" "+password);
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	

}
