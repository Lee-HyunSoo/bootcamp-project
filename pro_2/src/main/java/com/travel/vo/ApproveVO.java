package com.travel.vo;

public class ApproveVO {
	
	private int trvIdx;
	private String trvName;
	private String trvDepart;
	private String trvDest;
	private int trvPrice;
	private int trvTcnt;
	private int trvCcnt;
	private String trvDepDate;
	private String trvDestDate;
	private int userIdx;
	private String userName;

	public ApproveVO() {
	}

	public int getTrvIdx() {
		return trvIdx;
	}

	public void setTrvIdx(int trvIdx) {
		this.trvIdx = trvIdx;
	}

	public String getTrvName() {
		return trvName;
	}

	public void setTrvName(String trvName) {
		this.trvName = trvName;
	}

	public String getTrvDepart() {
		return trvDepart;
	}

	public void setTrvDepart(String trvDepart) {
		this.trvDepart = trvDepart;
	}

	public String getTrvDest() {
		return trvDest;
	}

	public void setTrvDest(String trvDest) {
		this.trvDest = trvDest;
	}

	public int getTrvPrice() {
		return trvPrice;
	}

	public void setTrvPrice(int trvPrice) {
		this.trvPrice = trvPrice;
	}

	public int getTrvTcnt() {
		return trvTcnt;
	}

	public void setTrvTcnt(int trvTcnt) {
		this.trvTcnt = trvTcnt;
	}

	public int getTrvCcnt() {
		return trvCcnt;
	}

	public void setTrvCcnt(int trvCcnt) {
		this.trvCcnt = trvCcnt;
	}

	public String getTrvDepDate() {
		return trvDepDate;
	}

	public void setTrvDepDate(String trvDepDate) {
		this.trvDepDate = trvDepDate;
	}

	public String getTrvDestDate() {
		return trvDestDate;
	}

	public void setTrvDestDate(String trvDestDate) {
		this.trvDestDate = trvDestDate;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "ApproveDAO [trvIdx=" + trvIdx + ", trvName=" + trvName + ", trvDepart=" + trvDepart + ", trvDest="
				+ trvDest + ", trvPrice=" + trvPrice + ", trvTcnt=" + trvTcnt + ", trvCcnt=" + trvCcnt + ", trvDepDate="
				+ trvDepDate + ", trvDestDate=" + trvDestDate + ", userIdx=" + userIdx + ", userName=" + userName + "]";
	}

}
