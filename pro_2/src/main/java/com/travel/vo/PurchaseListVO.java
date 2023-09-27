package com.travel.vo;

import java.util.Date;

public class PurchaseListVO {

	public PurchaseListVO() {
	}
	
	private int trv_idx = 0;
	private int user_idx = 0;
	private String trv_name = null;
	private String trv_depart = null;
	private String trv_dest = null;
	private int trv_price = 0;
	private int trv_tcnt = 0; //T.O
	private int trv_ccnt = 0; //current T.O
	private Date trv_deptdate = null;
	private Date trv_destdate = null;
	
	
	@Override
	public String toString() {
		return "purchaselistDAO [trv_idx=" + trv_idx + ", user_idx=" + user_idx + ", trv_name=" + trv_name
				+ ", trv_depart=" + trv_depart + ", trv_dest=" + trv_dest + ", trv_price=" + trv_price + ", trv_tcnt="
				+ trv_tcnt + ", trv_ccnt=" + trv_ccnt + ", trv_deptdate=" + trv_deptdate + ", trv_destdate="
				+ trv_destdate + "]";
	}
	public int getTrv_idx() {
		return trv_idx;
	}
	public void setTrv_idx(int trv_idx) {
		this.trv_idx = trv_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getTrv_name() {
		return trv_name;
	}
	public void setTrv_name(String trv_name) {
		this.trv_name = trv_name;
	}
	public String getTrv_depart() {
		return trv_depart;
	}
	public void setTrv_depart(String trv_depart) {
		this.trv_depart = trv_depart;
	}
	public String getTrv_dest() {
		return trv_dest;
	}
	public void setTrv_dest(String trv_dest) {
		this.trv_dest = trv_dest;
	}
	public int getTrv_price() {
		return trv_price;
	}
	public void setTrv_price(int trv_price) {
		this.trv_price = trv_price;
	}
	public int getTrv_tcnt() {
		return trv_tcnt;
	}
	public void setTrv_tcnt(int trv_tcnt) {
		this.trv_tcnt = trv_tcnt;
	}
	public int getTrv_ccnt() {
		return trv_ccnt;
	}
	public void setTrv_ccnt(int trv_ccnt) {
		this.trv_ccnt = trv_ccnt;
	}
	public Date getTrv_deptdate() {
		return trv_deptdate;
	}
	public void setTrv_deptdate(Date trv_deptdate) {
		this.trv_deptdate = trv_deptdate;
	}
	public Date getTrv_destdate() {
		return trv_destdate;
	}
	public void setTrv_destdate(Date trv_destdate) {
		this.trv_destdate = trv_destdate;
	}


}
