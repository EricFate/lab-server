package edu.whu.iss.wen.bean;

import java.util.Date;

import edu.whu.iss.bean.ChatGroup;

public class Notice {
	int id;
	int number;
	int type; //Course公告为0，AdminClass公告为1
	String name;
	String content;
	Date date;
	ChatGroup chatGroup;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ChatGroup getChatGroup() {
		return chatGroup;
	}
	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
	}

}
