package com.pro1.db;

import java.sql.*;

public class MySQLConnector {

    private final String dbName="db_cafe";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String id_mysql = "root";
    private final String pw_mysql = "0000"; // 본인 비밀번호

    public MySQLConnector() {
    }

    public Connection connectMySQL() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id_mysql, pw_mysql);
            System.out.println("MySQL 접속 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("Class.forName(driver) ERR: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("getConnection() ERR: " + e.getMessage());
        }
        return conn;
    }

}
