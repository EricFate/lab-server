package edu.whu.iss.bean;

import java.util.List;

public class ResultItem {
	private int type;
	private String title;
	private List<Info> infos;
	public ResultItem() {
		super();
	}

	public ResultItem(int type, String title, List<Info> infos) {
		super();
		this.type = type;
		this.title = title;
		this.infos = infos;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Info> getInfos() {
		return infos;
	}
	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
