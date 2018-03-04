package edu.whu.iss.lu.bean;

import java.util.Set;

import edu.whu.iss.bean.Student;

/**
 * Parent entity. @author MyEclipse Persistence Tools
 */

public class Parent implements java.io.Serializable {

	// Fields

	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String number;
	private String realname;
	private String region;
	private String phone;
	private String signature;
	private String imageUrl;
	private String gender;
	private String introduction;
	private String token;
	private String identity;
	private Set<Student> students;
	
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	private boolean hasAccountFlag;
	private boolean passwordWriteFlag;

	// Constructors

	public boolean isHasAccountFlag() {
		return hasAccountFlag;
	}

	public void setHasAccountFlag(boolean hasAccountFlag) {
		this.hasAccountFlag = hasAccountFlag;
	}

	public boolean isPasswordWriteFlag() {
		return passwordWriteFlag;
	}

	public void setPasswordWriteFlag(boolean passwordWriteFlag) {
		this.passwordWriteFlag = passwordWriteFlag;
	}

	/** default constructor */
	public Parent() {
	}

	/** full constructor */
	public Parent(String username, String password, String nickname, String email, String number, String realname,
			String region, String phone, String signature, String imageUrl, String gender, String introduction,
			String token, String identity) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.number = number;
		this.realname = realname;
		this.region = region;
		this.phone = phone;
		this.signature = signature;
		this.imageUrl = imageUrl;
		this.gender = gender;
		this.introduction = introduction;
		this.token = token;
		this.identity = identity;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

}