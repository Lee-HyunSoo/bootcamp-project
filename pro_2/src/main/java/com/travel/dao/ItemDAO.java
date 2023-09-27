package com.travel.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.travel.db.MySQLConnector;
import com.travel.vo.ItemVO;

public class ItemDAO extends MySQLConnector {

	public ItemDAO() {
		super();
	}

	public ArrayList<ItemVO> sellectItemadmin() {
		ArrayList<ItemVO> aditems = new ArrayList<ItemVO>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from item");

			while (rs.next()) {
				ItemVO item = new ItemVO();

				item.setTrv_idx(rs.getInt("TRVIDX"));
				item.setTrv_name(rs.getString("TRVNAME"));
				item.setTrv_depart(rs.getString("TRVDEPART"));
				item.setTrv_dest(rs.getString("TRVDEST"));
				item.setTrv_price(rs.getInt("TRVPRICE"));
				item.setTrv_tcnt(rs.getInt("TRVTCNT"));
				item.setTrv_ccnt(rs.getInt("TRVCCNT"));
				item.setTrv_deptdate(rs.getDate("TRVDEPDATE"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
				aditems.add(item);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(stmt, rs);
		}
		return aditems;
	}
	
	public ArrayList<ItemVO> sellectItemuser() {
		ArrayList<ItemVO> usitems = new ArrayList<ItemVO>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from item where trvdepdate > now() ");

			while (rs.next()) {
				ItemVO item = new ItemVO();

				item.setTrv_idx(rs.getInt("TRVIDX"));
				item.setTrv_name(rs.getString("TRVNAME"));
				item.setTrv_depart(rs.getString("TRVDEPART"));
				item.setTrv_dest(rs.getString("TRVDEST"));
				item.setTrv_price(rs.getInt("TRVPRICE"));
				item.setTrv_tcnt(rs.getInt("TRVTCNT"));
				item.setTrv_ccnt(rs.getInt("TRVCCNT"));
				item.setTrv_deptdate(rs.getDate("TRVDEPDATE"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
				usitems.add(item);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(stmt, rs);
		}
		return usitems;
	}
	
	
	public void insertNewitem (ItemVO item, String deptdate, String destdate) {
		String newitem_query = "insert into item (TRVNAME, TRVDEPART, TRVDEST, TRVPRICE, TRVTCNT, TRVCCNT, TRVDEPDATE, TRVDESTDATE) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(newitem_query);
			pstmt.setString(1, item.getTrv_name());
			pstmt.setString(2, item.getTrv_depart());
			pstmt.setString(3, item.getTrv_dest());
			pstmt.setInt(4, item.getTrv_price());
			pstmt.setInt(5, item.getTrv_tcnt());
			pstmt.setInt(6, item.getTrv_ccnt());
			pstmt.setString(7, deptdate);
			pstmt.setString(8, destdate);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}
	
	public void itempay (int trvidx, int useridx) {
		String itempay_query="insert into purchase (TRVIDX, USERIDX, TRVPAY, TRVAPPROVE) values (?, ?, 1, 0)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(itempay_query);
			pstmt.setInt(1, trvidx);
			pstmt.setInt(2, useridx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
	}
	
	
	public void itemadd (int trvidx, int useridx) {
		String itemadd_query="insert into purchase (TRVIDX, USERIDX, TRVPAY, TRVAPPROVE) values (?, ?, 0, 0)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(itemadd_query);
			pstmt.setInt(1, trvidx);
			pstmt.setInt(2, useridx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
	}
	
	
	public ItemVO itemDetail(int trvidx) {
		ItemVO item = new ItemVO();
		String itemdetial_query = "select*from item where TRVIDX =?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt=conn.prepareStatement(itemdetial_query);
			pstmt.setInt(1, trvidx);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				item.setTrv_idx(rs.getInt("TRVIDX"));
				item.setTrv_name(rs.getString("TRVNAME"));
				item.setTrv_depart(rs.getString("TRVDEPART"));
				item.setTrv_dest(rs.getString("TRVDEST"));
				item.setTrv_price(rs.getInt("TRVPRICE"));
				item.setTrv_tcnt(rs.getInt("TRVTCNT"));
				item.setTrv_ccnt(rs.getInt("TRVCCNT"));
				item.setTrv_deptdate(rs.getDate("TRVDEPDATE"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return item;
	}
	
	public void itemdelete(int idx) {
		String itemdelete_query = "delete from item where trvidx = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(itemdelete_query);
			pstmt.setInt(1, idx);
			int n = pstmt.executeUpdate();
			if (n > 0)
				System.out.println(" delete ");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}
	
	
	
	
	public ItemVO doGet_itemmodify(int trvidx) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ItemVO item = new ItemVO();
		String itemdoget_query = "select *from item where trvidx=?";
		
		try {
			psmt=conn.prepareStatement(itemdoget_query);
			psmt.setInt(1, trvidx);
			rs = psmt.executeQuery();
			while(rs.next()) {
				item.setTrv_idx(rs.getInt("TRVIDX"));
				item.setTrv_name(rs.getString("TRVNAME"));
				item.setTrv_depart(rs.getString("TRVDEPART"));
				item.setTrv_dest(rs.getString("TRVDEST"));
				item.setTrv_price(rs.getInt("TRVPRICE"));
				item.setTrv_tcnt(rs.getInt("TRVTCNT"));
				item.setTrv_ccnt(rs.getInt("TRVCCNT"));
				item.setTrv_deptdate(rs.getDate("TRVDEPDATE"));
				item.setTrv_destdate(rs.getDate("TRVDESTDATE"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException in doGet() : "+e.getMessage());
		} finally {
			close(psmt, rs);
		}
		return item;
	}
	
	public void doPost_itemmodify(int trvidx, String trvname, String trvdepart, String trvdest, int trvprice, int trvtcnt, String trvdepdate, String trvdestdate) {
	    PreparedStatement psmt = null;

	    String itemdopost_query = "UPDATE item set TRVNAME=?, TRVDEPART=?, TRVDEST=?, TRVPRICE=?, TRVTCNT=?, TRVDEPDATE=?, TRVDESTDATE=? WHERE TRVIDX=?";
	    try {
	        psmt = conn.prepareStatement(itemdopost_query);
	        psmt.setString(1, trvname);
	        psmt.setString(2, trvdepart);
	        psmt.setString(3, trvdest);
	        psmt.setInt(4, trvprice);
	        psmt.setInt(5, trvtcnt);
	        psmt.setString(6, trvdepdate);
	        psmt.setString(7, trvdestdate);
	        psmt.setInt(8, trvidx);
	        psmt.executeUpdate();
	        

	    } catch (SQLException e) {
	        System.out.println("SQLException in doPost() : " + e.getMessage());
	    } finally {
	        close(psmt);
	    }
	}
	
}
