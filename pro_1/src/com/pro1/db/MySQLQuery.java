package com.pro1.db;

import com.pro1.domain.Cafe;
import com.pro1.domain.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLQuery extends Close {

    private final MySQLConnector mySQLConnector = new MySQLConnector();
    private final Connection conn = mySQLConnector.connectMySQL();

    public MySQLQuery() {
    }

    public Map<String, ArrayList<Cafe>> selectAllCafe() {
        Map<String, ArrayList<Cafe>> cafes = new HashMap<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from cafe");
            while(rs.next()) {
                Cafe cafeData = new Cafe(rs.getString("store"), rs.getString("menu"), rs.getInt("price"), rs.getInt("isCoupon"));
                if (!cafes.containsKey(rs.getString("store")))
                    cafes.computeIfAbsent(rs.getString("store"), k -> new ArrayList<>()).add(cafeData);
                else
                    cafes.computeIfPresent(rs.getString("store"), (k, v) -> v).add(cafeData);
            }
        } catch (SQLException e) {
            System.out.println("selectAllCafe ERR: " + e.getMessage());
        } finally {
            this.close(stmt, rs);
        }
        return cafes;
    }

    public Cafe selectOneCafe(String store, String menu) {
        Cafe cafe = new Cafe();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement("select * from cafe where store = ? and menu = ?");
            pstmt.setString(1, store);
            pstmt.setString(2, menu);
            rs = pstmt.executeQuery();
            while (rs.next())
                cafe = new Cafe(rs.getString("store"), rs.getString("menu"), rs.getInt("price"), rs.getInt("isCoupon"));
        } catch (SQLException e) {
            System.out.println("selectOneCafe ERR: " + e.getMessage());
        } finally {
            this.close(pstmt, rs);
        }
        return cafe;
    }

    public ArrayList<Coupon> selectAllCoupon() {
        ArrayList<Coupon> coupons = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from coupon");
            while (rs.next())
                coupons.add(new Coupon(rs.getString("store"), rs.getString("menu")));
        } catch (SQLException e) {
            System.out.println("selectAllCoupon ERR: " + e.getMessage());
        } finally {
            this.close(stmt, rs);
        }
        return coupons;
    }

    public void updateCafeByCoupon(ArrayList<Coupon> coupons) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("update cafe set isCoupon = ? where store = ? and menu = ?");
            for (Coupon c : coupons) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, c.getStore());
                pstmt.setString(3, c.getMenu());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("updateCafeByCoupon ERR: " + e.getMessage());
        } finally {
            this.close(pstmt);
        }
    }

    public void updateCafeUseCoupon(boolean couponUse, String store, String menu) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("update cafe set isCoupon = ? where store = ? and menu = ?");
            pstmt.setInt(1, couponUse ? 0 : 1); // 추후 front에서 예 / 아니오를 눌렀을 때 값을 true / false로 받아 처리하기 위해
            pstmt.setString(2, store);
            pstmt.setString(3, menu);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateCafeUseCoupon ERR: " + e.getMessage());
        } finally {
            this.close(pstmt);
        }
    }

    public void deleteUseCoupon(String store, String menu) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("delete from coupon where store = ? and menu = ?");
            pstmt.setString(1, store);
            pstmt.setString(2, menu);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteUseCoupon ERR: " + e.getMessage());
        } finally {
            this.close(pstmt);
        }
    }

    public void insertCafePayment(Cafe cafe) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("insert into cafepayment values (NULL, ?, ?, ?, ?)");
            pstmt.setString(1, cafe.getStore());
            pstmt.setString(2, cafe.getMenu());
            pstmt.setInt(3, cafe.getPrice());
            pstmt.setString(4, cafe.getTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertCafePayment ERR: " + e.getMessage());
        } finally {
            this.close(pstmt);
        }
    }

}
