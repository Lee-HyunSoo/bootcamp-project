package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.travel.db.MySQLConnector;
import com.travel.vo.AdminVO;
import com.travel.vo.MsgVO;

public class MsgDAO extends MySQLConnector {

	public MsgDAO() {
		super();
	}

	public ArrayList<MsgVO> loadMsg(String sessionID) {// 세션에서 id값을 추출하여 넣고 조건에 맞는 글만 가져오는 메소드.
		String loadMsgQuery = "SELECT * FROM message WHERE MSGSENDER = ? OR MSGRECEIVER = ? ORDER BY MSGIDX DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MsgVO> msg = null;

		try {
			msg = new ArrayList<MsgVO>();
			pstmt = conn.prepareStatement(loadMsgQuery);
			pstmt.setString(1, sessionID);
			pstmt.setString(2, sessionID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int msgIDX = rs.getInt("MSGIDX");
				String msgName = rs.getString("MSGNAME");
				String msgSender = rs.getString("MSGSENDER");
				String msgReceiver = rs.getString("MSGRECEIVER");
				String msgTitle = rs.getString("MSGTITLE");
				String msgContent = rs.getString("MSGCONTENT");
				String msgAskDate = rs.getString("MSGASKDATE");
				String msgAnswerDate = rs.getString("MSGANSWERDATE");
				boolean msgUserIsRead = rs.getBoolean("MSGUSERISREAD");
				boolean msgAdminIsRead = rs.getBoolean("MSGADMINISREAD");
				boolean msgAdminAnswer = rs.getBoolean("MSGADMINANSWER");

				msg.add(new MsgVO(msgIDX, msgName, msgSender, msgReceiver, msgTitle, msgContent, msgAskDate,
						msgAnswerDate, msgUserIsRead, msgAdminIsRead, msgAdminAnswer));
			}
		} catch (SQLException e) {
			System.out.println("loadMsg ERR =>>" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return msg;
	}// loadMsg() END

	public MsgVO msgDetail(int msgIdx) {// 선택한 메세지의 상세보기 페이지
		String msgDetailQuery = "SELECT * FROM message WHERE MSGIDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MsgVO detail = null;

		try {
			pstmt = conn.prepareStatement(msgDetailQuery);
			pstmt.setInt(1, msgIdx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int msgIDX = rs.getInt("msgIDX");
				String msgName = rs.getString("MSGNAME");
				String msgSender = rs.getString("MSGSENDER");
				String msgReceiver = rs.getString("MSGRECEIVER");
				String msgTitle = rs.getString("MSGTITLE");
				String msgContent = rs.getString("MSGCONTENT");
				String msgAskDate = rs.getString("MSGASKDATE");
				String msgAnswerDate = rs.getString("MSGANSWERDATE");
				boolean msgUserIsRead = rs.getBoolean("MSGUSERISREAD");
				boolean msgAdminIsRead = rs.getBoolean("MSGADMINISREAD");
				boolean msgAdminAnswer = rs.getBoolean("MSGADMINANSWER");

				detail = new MsgVO(msgIDX, msgName, msgSender, msgReceiver, msgTitle, msgContent, msgAskDate,
						msgAnswerDate, msgUserIsRead, msgAdminIsRead, msgAdminAnswer);
			}
		} catch (SQLException e) {
			System.out.println("msgDetail ERR ==>>" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return detail;

	}// msgDetail() END

	/** 유저 메세지 관련 메서드 */
	public void askMsg(String msgTitle, String msgName, String msgSender, String msgContent) {
		// 유저가 등록한 문의글을 DB에 저장하는 메소드
		String askMsgQuery = "INSERT INTO message (MSGNAME, MSGSENDER, MSGRECEIVER, MSGTITLE, MSGCONTENT, MSGASKDATE, MSGUSERISREAD) values (?, ?, 'adminQNA', ?, ?, now(), true)";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(askMsgQuery);
			pstmt.setString(1, msgName);
			pstmt.setString(2, msgSender);
			pstmt.setString(3, msgTitle);
			pstmt.setString(4, msgContent);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("askMsg ERR =>>" + e.getMessage());
		} finally {
			close(pstmt);
		}
	}// askMsg() END

	public void userIsRead(int msgIDX) {// 유저가 답변받은 문의글 조회했는지 확인하는 메소드
		String userIsReadQuery = "update message set MSGUSERISREAD = true where MSGIDX = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(userIsReadQuery);
			pstmt.setInt(1, msgIDX);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("userIsRead ERR ==>> " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}// userIsRead() END

	/** admin 메세지 관련 메서드들 */
	public ArrayList<AdminVO> getAdmin() {// 관리자 정보 조회하는 메소드
		String getAdminQuery = "SELECT * FROM admin";
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<AdminVO> admin = null;

		try {
			admin = new ArrayList<AdminVO>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(getAdminQuery);
			while (rs.next()) {
				int adminIdx = rs.getInt("ADMINIDX");
				String adminId = rs.getString("ADMINID");
				String adminPw = rs.getString("ADMINPW");
				String adminName = rs.getString("ADMINNAME");
				String adminEmail = rs.getString("ADMINEMAIL");
				String adminTel = rs.getString("ADMINTEL");
				String adminRegDate = rs.getString("ADMINREGDATE");

				admin.add(new AdminVO(adminIdx, adminId, adminPw, adminName, adminEmail, adminTel, adminRegDate));
			}
		} catch (SQLException e) {
			System.out.println("getAdmin ERR ==>>" + e.getMessage());
		} finally {
			close(stmt, rs);
		}
		return admin;
	}// getAdmin() END

	public void answerMsg(String msgName, String msgSender, String msgReceiver, String msgTitle, String msgContent,
			String msgAskDate) {
		// 답변한 메세지를 db에 저장하는 메서드
		String askMsgQuery = "INSERT INTO message (MSGNAME, MSGSENDER, MSGRECEIVER, MSGTITLE, MSGCONTENT, MSGASKDATE, MSGANSWERDATE, MSGADMINANSWER) values (?, ?, ?, ?, ?, ?, now(), true)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(askMsgQuery);
			pstmt.setString(1, msgName);
			pstmt.setString(2, msgSender);
			pstmt.setString(3, msgReceiver);
			pstmt.setString(4, msgTitle);
			pstmt.setString(5, msgContent);
			pstmt.setString(6, msgAskDate);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("answerMsg ERR =>>" + e.getMessage());
		} finally {
			close(pstmt);
		}
	}// answerMsg() END

	public void answerStatus(int msgIDX) {// 관리자가 응답하면 응답여부를 업데이트하는 메소드.
		String answerStatusQuery = "UPDATE message SET MSGANSWERDATE = now(), MSGADMINANSWER = true where MSGIDX = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(answerStatusQuery);
			pstmt.setInt(1, msgIDX);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("answerStatus ERR ==>>" + e.getMessage());
		}

	}// answerStatus() END

	public void adminIsRead(int msgIDX) {// 관리자가 문의글을 읽었는지 여부 확인 메소드
		String adminIsReadQuery = "UPDATE message set MSGADMINISREAD = true where MSGIDX = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(adminIsReadQuery);
			pstmt.setInt(1, msgIDX);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("adminIsRead ERR ==>> " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}// adminIsRead() END

}
