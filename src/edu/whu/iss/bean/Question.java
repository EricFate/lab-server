package edu.whu.iss.bean;

/**
 * Created by fate on 2016/11/27.
 */

public class Question {
    private int id;
    private String detail;
    private String answer;
    private String answerer;



    public Question(int i, String string) {
		this.id = i; 
		this.detail = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerer() {
		return answerer;
	}

	public void setAnswerer(String answerer) {
		this.answerer = answerer;
	}

	public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
