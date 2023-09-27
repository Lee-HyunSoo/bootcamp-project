package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.db.MySQLConnector;
import com.travel.vo.UserVO;

public class UserDAO extends MySQLConnector {

	public UserDAO() {
		super();
	}
	
	public UserVO selectUserDAO(String id, String pw) {
		UserVO user = new UserVO();
		String query = "select * from USER where USERID = ? AND USERPW = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setUserIdx(rs.getInt("USERIDX"));
				user.setUserId(rs.getString("USERID"));
				user.setUserPw(rs.getString("USERPW"));
				user.setUserName(rs.getString("USERNAME"));
				user.setUserEmail(rs.getString("USEREMAIL"));
				user.setUserTel(rs.getString("USERTEL"));
				user.setUserRegDate(rs.getString("USERREGDATE"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return user;
	}
	
	public boolean selectUserSignUp(String inputId) {
		String query = "select USERID from USER where USERID = ?";
		String id = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getString("USERID");
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

	public void insertUserSignUp(UserVO user) {
		String query = "insert into USER values (null, ?, ?, ?, ?, ?, now())";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getUserTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}

}
