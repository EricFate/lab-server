package edu.whu.iss.bean;

import java.util.Date;

public class MessageRecord {
	private int id;
	private int number;
	private Date time;
	private String fromUid;
	private String toUid;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getFromUid() {
		return fromUid;
	}
	public void setFromUid(String fromUid) {
		this.fromUid = fromUid;
	}
	public String getToUid() {
		return toUid;
	}
	public void setToUid(String toUid) {
		this.toUid = toUid;
	}
	
}
