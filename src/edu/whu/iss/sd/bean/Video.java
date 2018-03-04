package edu.whu.iss.sd.bean;

public class Video {
	private String videoTitle;
	private int videoId;
	private String videoUrl;
	private int playnumber;
	private String imageUrl;
	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	/**
	 * @return the playnumber
	 */
	public int getPlaynumber() {
		return playnumber;
	}


	/**
	 * @param playnumber the playnumber to set
	 */
	public void setPlaynumber(int playnumber) {
		this.playnumber = playnumber;
	}


	private CollegeStudent collegeStudent;
	/**
	 * @return the collegeStudent
	 */
	public CollegeStudent getCollegeStudent() {
		return collegeStudent;
	}


	/**
	 * @param collegeStudent the collegeStudent to set
	 */
	public void setCollegeStudent(CollegeStudent collegeStudent) {
		this.collegeStudent = collegeStudent;
	}


	/**
	 * @return the videoUrl
	 */
	public String getVideoUrl() {
		return videoUrl;
	}


	/**
	 * @param videoUrl the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	

	/**
	 * @return the videoTitle
	 */
	public String getVideoTitle() {
		return videoTitle;
	}
	/**
	 * @param videoTitle the videoTitle to set
	 */
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
	/**
	 * @return the videoId
	 */
	public int getVideoId() {
		return videoId;
	}


	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}


	/**
	 * @return the videoUrl
	 */

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Video [videoTitle=" + videoTitle + ", videoId=" + videoId
				+ ", videoUrl=" + videoUrl + "]";
	}
	
	

}
