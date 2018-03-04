package edu.whu.iss.bean;

public class Info {
	public Info() {
		super();
	}
	private String uid;
	private String username;
	private String nickname;
	private String remark;
	private String imageURL;
	private String signature;
	

	
	public Info(String uid, String username, String nickname, String remark,
			String imageURL, String signature) {
		super();
		this.uid = uid;
		this.username = username;
		this.nickname = nickname;
		this.remark = remark;
		this.imageURL = imageURL;
		this.signature = signature;
	}
	public Info(String uid, String username, String nickname, String imageURL,
			String signature) {
		super();
		this.uid = uid;
		this.username = username;
		this.nickname = nickname;
		this.imageURL = imageURL;
		this.signature = signature;
	}
	public Info(String uid, String nickname, String imageURL, String signature) {
		super();
		this.uid = uid;
		this.nickname = nickname;
		this.imageURL = imageURL;
		this.signature = signature;
	}
	public Info(String uid, String nickname, String imageURL) {
		super();
		this.uid = uid;
		this.nickname = nickname;
		this.imageURL = imageURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getId() {
		return uid;
	}
	public void setId(String id) {
		this.uid = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
