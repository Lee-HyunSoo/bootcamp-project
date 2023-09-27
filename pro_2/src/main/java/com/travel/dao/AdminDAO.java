package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.db.MySQLConnector;
import com.travel.vo.AdminVO;

public class AdminDAO extends MySQLConnector {

	public AdminDAO() {
		super();
	}
	
	public AdminVO selectAdminDAO(String id, String pw) {
		AdminVO admin = new AdminVO();
		String query = "select * from ADMIN where ADMINID = ? AND ADMINPW = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				admin.setAdminIdx(rs.getInt("ADMINIDX"));
				admin.setAdminId(rs.getString("ADMINID"));
				admin.setAdminPw(rs.getString("ADMINPW"));
				admin.setAdminName(rs.getString("ADMINNAME"));
				admin.setAdminEmail(rs.getString("ADMINEMAIL"));
				admin.setAdminTel(rs.getString("VTEL"));
				admin.setAdminRegDate(rs.getString("ADMINREGDATE"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return admin;
	}
	
	public boolean selectAdminSignUp(String inputId) {
		String query = "select ADMINID from ADMIN where ADMINID = ?";
		String id = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getString("ADMINID");
				System.out.println(id);
				if (id != null)
					return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return false;
	}
	
	public void insertAdminSignUp(AdminVO admin) {
		String query = "insert into ADMIN values (null, ?, ?, ?, ?, ?, now())";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, admin.getAdminId());
			pstmt.setString(2, admin.getAdminPw());
			pstmt.setString(3, admin.getAdminName());
			pstmt.setString(4, admin.getAdminEmail());
			pstmt.setString(5, admin.getAdminTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}

}
