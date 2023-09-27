package com.pro1.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Close {
    public void close(Statement stmt, ResultSet rs) {
        try {
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Statement, ResultSet CLOSE ERR : " + e.getMessage());
        }
    }

    public void close(PreparedStatement pstmt, ResultSet rs) {
        try {
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("PreparedStatement, ResultSet CLOSE ERR : " + e.getMessage());
        }
    }

    public void close(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("PreparedStatement CLOSE ERR : " + e.getMessage());
        }
    }

}
