package edu.whu.iss.bean;

public class UserGroup {
	private int id;
	private ChatGroup chatGroup;
	private String uid;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public ChatGroup getChatGroup() {
		return chatGroup;
	}
	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
	}
}
