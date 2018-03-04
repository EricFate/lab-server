package edu.whu.iss.wen.bean;


public class KnowledgeRelationship {
	private int id;
	private Knowledge knowledge1;
	private String relationship;
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	private Knowledge knowledge2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Knowledge getKnowledge1() {
		return knowledge1;
	}
	public void setKnowledge1(Knowledge knowledge1) {
		this.knowledge1 = knowledge1;
	}
	public Knowledge getKnowledge2() {
		return knowledge2;
	}
	public void setKnowledge2(Knowledge knowledge2) {
		this.knowledge2 = knowledge2;
	}
	
	

}
