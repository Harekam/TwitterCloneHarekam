package com.csc.bean;

public class PersonBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String type;
	private String message;
	private int active;

	private String timestamp;
	private String followingId;
	private boolean following;

	private String oldPassword;
	private int tweet_id;

	public String setUserName(String userName) {
		return this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String setPassword(String password) {
		return this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String setFullName(String fullName) {
		return this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public boolean isFollowing() {
		return following;
	}

	public void setIsFollowing(boolean following) {
		this.following = following;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public int getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(int tweet_id) {
		this.tweet_id = tweet_id;
	}

	



}
