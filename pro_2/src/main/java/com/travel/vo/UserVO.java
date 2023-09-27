package com.travel.vo;

public class UserVO {
	
	private int userIdx;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String userTel;
	private String userRegDate;
	
	public UserVO() {
	}
	
	

	public UserVO(int userIdx, String userId, String userPw, String userName, String userEmail, String userTel,
			String userRegDate) {
		super();
		this.userIdx = userIdx;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userTel = userTel;
		this.userRegDate = userRegDate;
	}



	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}

	@Override
	public String toString() {
		return "User [userIdx=" + userIdx + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", userTel=" + userTel + ", userRegDate=" + userRegDate + "]";
	}

}
