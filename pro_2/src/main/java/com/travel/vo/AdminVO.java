package com.travel.vo;

public class AdminVO {

	private int adminIdx;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminEmail;
	private String adminTel;
	private String adminRegDate;
	
	public AdminVO() {
	}

	
	
	public AdminVO(int adminIdx, String adminId, String adminPw, String adminName, String adminEmail, String adminTel,
			String adminRegDate) {
		this.adminIdx = adminIdx;
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminTel = adminTel;
		this.adminRegDate = adminRegDate;
	}



	public int getAdminIdx() {
		return adminIdx;
	}

	public void setAdminIdx(int adminIdx) {
		this.adminIdx = adminIdx;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public String getAdminRegDate() {
		return adminRegDate;
	}

	public void setAdminRegDate(String adminRegDate) {
		this.adminRegDate = adminRegDate;
	}

	@Override
	public String toString() {
		return "Admin [adminIdx=" + adminIdx + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + ", adminEmail=" + adminEmail + ", adminTel=" + adminTel + ", adminRegDate=" + adminRegDate
				+ "]";
	}

}
