package edu.whu.iss.bean;

/**
 * Created by fate on 2016/11/17.
 */

public class Result {
    private int code;
    private String message;
    private int id;
    private String username;
    private String nickname;
    private String imageURL;
    private String signiture;
    private String token;
    private String grade;
    private boolean isLearning;
    private int count;
    
    public Result(int code, int count) {
		super();
		this.code = code;
		this.count = count;
	}

	public Result(int code, boolean isLearning) {
		super();
		this.code = code;
		this.isLearning = isLearning;
	}

	public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

	public Result(int code, String message, String token) {
		super();
		this.code = code;
		this.message = message;
		this.token = token;
	}

	public Result(int code, String message, int id, String username,
			String nickname, String imageURL, String signiture, String token,String grade) {
		super();
		this.code = code;
		this.message = message;
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.imageURL = imageURL;
		this.signiture = signiture;
		this.token = token;
		this.grade = grade;
	}

	public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSigniture() {
        return signiture;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isLearning() {
		return isLearning;
	}


	public void setLearning(boolean isLearning) {
		this.isLearning = isLearning;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
