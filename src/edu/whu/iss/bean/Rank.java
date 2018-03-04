package edu.whu.iss.bean;

import java.util.Date;

import edu.whu.iss.wen.bean.Course;

/**
 * Created by fate on 2016/12/2.
 */

public class Rank {
	private int id;
    private String content;
    private float rank;
    private Student ranker;
    private Course course;
    private Date time;
    public Rank() {
		super();
	}

	public Rank(String content, float rank) {
        this.content = content;
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public float getRank() {
		return rank;
	}

	public void setRank(float rank) {
		this.rank = rank;
	}

	public Student getRanker() {
        return ranker;
    }

    public void setRanker(Student ranker) {
        this.ranker = ranker;
    }



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
