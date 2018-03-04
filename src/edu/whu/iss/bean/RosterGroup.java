package edu.whu.iss.bean;

import java.util.HashSet;
import java.util.Set;

public class RosterGroup {
	private int id;
	private String name;
	private String uno;
	private Set<Roster> rosters = new HashSet<Roster>();
	
	public RosterGroup(String name, String uno) {
		super();
		this.name = name;
		this.uno = uno;
	}
	public RosterGroup() {
		// TODO Auto-generated constructor stub
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

	public Set<Roster> getRosters() {
		return rosters;
	}
	public void setRosters(Set<Roster> rosters) {
		this.rosters = rosters;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	
}
