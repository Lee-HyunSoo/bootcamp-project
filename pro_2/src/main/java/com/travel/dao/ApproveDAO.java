package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.travel.db.MySQLConnector;
import com.travel.vo.ApproveVO;

public class ApproveDAO extends MySQLConnector {

	public ApproveDAO() {
		super();
	}
	
	public ArrayList<ApproveVO> selectApproveDAO() {
		ArrayList<ApproveVO> approves = new ArrayList<ApproveVO>();
		ApproveVO approve = new ApproveVO();
		String query = "select DISTINCT i.trvidx, i.trvname, i.trvdepart, i.trvdest, i.trvprice, i.trvtcnt, i.trvccnt, i.trvdepdate, i.trvdestdate, u.useridx, u.username \r\n"
				+ "from PURCHASE as p LEFT JOIN ITEM as i on p.trvidx = i.trvidx AND p.trvpay=true AND p.trvapprove = false LEFT JOIN USER as u on p.useridx = u.useridx where i.trvidx is not null;";

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				approve.setTrvIdx(rs.getInt("TRVIDX"));
				approve.setTrvName(rs.getString("TRVNAME"));
				approve.setTrvDepart(rs.getString("TRVDEPART"));
				approve.setTrvDest(rs.getString("TRVDEST"));
				approve.setTrvPrice(rs.getInt("TRVPRICE"));
				approve.setTrvTcnt(rs.getInt("TRVTCNT"));
				approve.setTrvCcnt(rs.getInt("TRVCCNT"));
				approve.setTrvDepDate(rs.getString("TRVDEPDATE"));
				approve.setTrvDestDate(rs.getString("TRVDESTDATE"));
				approve.setUserIdx(rs.getInt("USERIDX"));
				approve.setUserName(rs.getString("USERNAME"));
				approves.add(approve);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(stmt, rs);
		}
		return approves;
	}

	public void updateApprovePurchase(int trvIdx, int userIdx) { // 테이블 데이터 수정
		String query = "update purchase set TRVAPPROVE = TRUE where TRVIDX = ? AND USERIDX = ?";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, trvIdx);
			pstmt.setInt(2, userIdx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}
	
	public void updateApproveItem(int trvIdx) { // 테이블 데이터 수정
		String query = "update ITEM set TRVCCNT = case when TRVCCNT >= TRVTCNT then TRVTCNT "
				+ "else TRVCCNT + 1 END where TRVIDX = ?";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, trvIdx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}

}
