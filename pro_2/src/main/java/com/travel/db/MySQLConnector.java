package com.travel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.travel.vo.AdminVO;
import com.travel.vo.ApproveVO;
import com.travel.vo.ItemVO;
import com.travel.vo.MsgVO;
import com.travel.vo.PurchaseListVO;
import com.travel.vo.UserVO;

public class MySQLConnector {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String db_name = "team1_travel";
	private String url = "jdbc:mysql://localhost:3306/" + db_name
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private String id_mysql = "root";
	private String pw_mysql = "0000";

	public Connection conn = null; // MySQL 접속 결과(상태) 저장

	public MySQLConnector() {
		this.connectMySQL();
	}	
	
	private void connectMySQL() {
		try {
			Class.forName(driver);
			System.out.println("driver load 성공");
			conn = DriverManager.getConnection(url, id_mysql, pw_mysql);
			System.out.println("MySQL 접속 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	public void close(Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Close SQLException: " + e.getMessage());
		}
	}

	public void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Close SQLException: " + e.getMessage());
		}
	}

	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Close SQLException: " + e.getMessage());
		}
	}

}