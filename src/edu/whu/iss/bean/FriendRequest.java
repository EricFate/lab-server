package edu.whu.iss.bean;

public class FriendRequest {
	private int id;
	private String uidFrom;
	private String uidTo;
	private String remark;
	private String groupName;
	private String nickname;
	private String imageURL;
	private String message;
	public FriendRequest() {
		super();
	}

	public FriendRequest(int id, String from, String nickname, String imageURL,
			String message) {
		super();
		this.id = id;
		this.uidFrom = from;
		this.nickname = nickname;
		this.imageURL = imageURL;
		this.message = message;
	}



	public FriendRequest(String uidFrom, String uidTo, String remark,
			String groupName) {
		super();
		this.uidFrom = uidFrom;
		this.uidTo = uidTo;
		this.remark = remark;
		this.setGroupName(groupName);
	}

	public String getUidFrom() {
		return uidFrom;
	}

	public void setUidFrom(String uidFrom) {
		this.uidFrom = uidFrom;
	}

	public String getUidTo() {
		return uidTo;
	}

	public void setUidTo(String uidTo) {
		this.uidTo = uidTo;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
